import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Patch,
  UseGuards,
} from '@nestjs/common';
import { ApiOperation, ApiParam } from '@nestjs/swagger';
import { UserService } from './user.service';
import { AuthGuard } from '../common/guard/';
import { SafeUser } from '../common/types/';
import { GetUser } from '../common/decorator';
import { EditUserDto } from './dto';
import { ApiBearerAuth } from '@nestjs/swagger';

@ApiBearerAuth()
@Controller('users')
@UseGuards(AuthGuard)
export class UserController {
  constructor(private readonly usersService: UserService) {}

  @Get('me')
  @ApiOperation({
    summary: 'Get current user profile',
    description: 'Retrieve the authenticated user profile information',
  })
  async getMe(@GetUser('id') userId: string): Promise<SafeUser> {
    return this.usersService.getMe(userId);
  }

  @Get(':id')
  @ApiOperation({
    summary: 'Get user by ID',
    description: 'Retrieve a specific user by their unique identifier',
  })
  @ApiParam({
    name: 'id',
    description: 'Unique identifier of the user',
    example: 'clw8x5r4k0000pz8q9z9z9z9z',
  })
  async findOne(@Param('id') id: string): Promise<SafeUser> {
    return this.usersService.findById(id);
  }

  @Patch()
  @ApiOperation({
    summary: 'Update current user',
    description: 'Update the authenticated user profile information',
  })
  async updateUser(
    @GetUser('id') userId: string,
    @Body() dto: EditUserDto,
  ): Promise<SafeUser> {
    return this.usersService.updateUser(userId, dto);
  }

  @Delete()
  @ApiOperation({
    summary: 'Delete current user',
    description: 'Delete the authenticated user account',
  })
  async deleteUser(@GetUser('id') id: string): Promise<SafeUser> {
    return this.usersService.deleteUser(id);
  }
}
