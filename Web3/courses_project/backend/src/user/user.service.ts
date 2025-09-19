import { Injectable } from '@nestjs/common';
import { PrismaService } from '../prisma/prisma.service';
import { excludeUserHash, handleException } from '../common/helper';
import { SafeUser } from '../common/types/';
import { EditUserDto } from './dto';
import { PRISMA_ERROR_CODES } from '../common/constant';

@Injectable()
export class UserService {
  constructor(private prisma: PrismaService) {}

  async findById(id: string): Promise<SafeUser> {
    const user = await this.prisma.user.findUnique({
      where: { id },
    });

    if (!user) {
      handleException(PRISMA_ERROR_CODES.RECORD_NOT_FOUND, 'User Not Found');
    }

    return excludeUserHash(user);
  }

  async getMe(userId: string): Promise<SafeUser> {
    return this.findById(userId);
  }

  async updateUser(id: string, dto: EditUserDto): Promise<SafeUser> {
    try {
      await this.findById(id);

      const updatedUser = await this.prisma.user.update({
        where: { id },
        data: {
          ...dto,
        },
      });

      return excludeUserHash(updatedUser);
    } catch (error) {
      handleException(error);
    }
  }

  async deleteUser(id: string): Promise<SafeUser> {
    const user = await this.prisma.user.delete({
      where: { id },
    });

    if (!user) {
      handleException(
        PRISMA_ERROR_CODES.RECORD_NOT_FOUND,
        `User with id ${id} not found`,
      );
    }

    return excludeUserHash(user);
  }
}
