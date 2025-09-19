import {
  CanActivate,
  ExecutionContext,
  Injectable,
  UnauthorizedException,
  Inject,
} from '@nestjs/common';
import { ConfigService } from '@nestjs/config';
import { JwtService } from '@nestjs/jwt';
import { Request } from 'express';
import { excludeUserHash } from '../helper';
import { PrismaService } from '../../prisma/prisma.service';
import { payload } from 'src/common/types/jwt.type';
import { CACHE_MANAGER } from '@nestjs/cache-manager';
import * as cacheManager from 'cache-manager';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(
    private jwtService: JwtService,
    private config: ConfigService,
    private prisma: PrismaService,
    @Inject(CACHE_MANAGER) private cache: cacheManager.Cache,
  ) {}

  async canActivate(context: ExecutionContext): Promise<boolean> {
    const request = context.switchToHttp().getRequest<Request>();
    const token = this.extractTokenFromHeader(request);

    if (!token) throw new UnauthorizedException();

    try {
      const payload: payload & { jti: string } =
        await this.jwtService.verifyAsync(token, {
          secret: this.config.get<string>('JWT_SECRET'),
        });

      // 1) Check blacklist
      const isBlacklisted = await this.cache.get<boolean>(
        `blacklist:${payload.jti}`,
      );
      if (isBlacklisted)
        throw new UnauthorizedException('Access token revoked');

      // 2) Load user (optional, if you want fresh role/name info)
      const user = await this.prisma.user.findUnique({
        where: { id: payload.sub },
      });
      if (!user) throw new UnauthorizedException();

      request['user'] = excludeUserHash(user);
      return true;
    } catch {
      throw new UnauthorizedException();
    }
  }

  private extractTokenFromHeader(request: Request): string | undefined {
    const [type, token] = request.headers.authorization?.split(' ') ?? [];
    return type?.toLowerCase() === 'bearer' ? token : undefined;
  }
}
