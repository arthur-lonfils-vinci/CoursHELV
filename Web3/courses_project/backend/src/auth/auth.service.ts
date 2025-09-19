import { Injectable } from '@nestjs/common';
import { PrismaService } from '../prisma/prisma.service';
import { LoginDto, RegisterDto } from './dto';
import { RefreshTokenDto } from './dto/';
import * as argon from 'argon2';
import { JwtService } from '@nestjs/jwt';
import { excludeUserHash, handleException } from '../common/helper';
import { APP_ERROR_CODE } from '../common/constant';
import { randomUUID } from 'crypto';
import { SafeUser } from '../common/types';
import { SecurityService } from '../security/security.service';
import * as cacheManager from 'cache-manager';
import { Inject as InjectNest } from '@nestjs/common';
import { CACHE_MANAGER } from '@nestjs/cache-manager';

@Injectable()
export class AuthService {
  constructor(
    private prisma: PrismaService,
    private jwt: JwtService,
    private security: SecurityService,
    @InjectNest(CACHE_MANAGER) private cache: cacheManager.Cache,
  ) {}

  async register(dto: RegisterDto): Promise<SafeUser> {
    try {
      const hash = await argon.hash(dto.password);
      const user = await this.prisma.user.create({
        data: {
          email: dto.email,
          firstname: dto.firstname,
          lastname: dto.lastname,
          passwordHash: hash,
        },
      });
      return excludeUserHash(user);
    } catch (error) {
      handleException(error);
    }
  }

  async login(
    dto: LoginDto,
    ip?: string,
  ): Promise<{ access_token: string; refresh_token: string }> {
    const user = await this.prisma.user.findUnique({
      where: { email: dto.email },
    });

    if (!user || !(await argon.verify(user.passwordHash, dto.password))) {
      this.security.logFailedLogin(dto.email, ip);
      handleException(APP_ERROR_CODE.UNAUTHORIZED, 'Invalid credentials');
    }

    this.security.logSecurityEvent('SUCCESSFUL_LOGIN', user.id, ip, {
      email: user.email,
      rememberMe: !!dto.rememberMe,
    });

    const refreshTtlSec = dto.rememberMe ? 60 * 60 * 24 * 30 : 60 * 60 * 2; // 30d or 2h
    return this.generateTokens(
      user.id,
      user.email,
      refreshTtlSec,
      !!dto.rememberMe,
      ip,
    );
  }

  private async generateTokens(
    userId: string,
    email: string,
    refreshTtlSec: number,
    rememberMe: boolean,
    ip?: string,
  ): Promise<{ access_token: string; refresh_token: string }> {
    const accessJti = randomUUID();

    const accessToken = await this.jwt.signAsync(
      { sub: userId, email, jti: accessJti },
      { expiresIn: '15m' },
    );

    // Opaque refresh token stored in Redis
    const refreshToken = randomUUID();
    await this.cache.set(
      `refresh:${refreshToken}`,
      { userId, rememberMe },
      refreshTtlSec * 1000,
    );

    this.security.logSecurityEvent('TOKEN_ISSUED', userId, ip, { rememberMe });

    return { access_token: accessToken, refresh_token: refreshToken };
  }

  async refreshToken(
    dto: RefreshTokenDto,
    ip?: string,
  ): Promise<{ access_token: string; refresh_token: string }> {
    const stored = await this.cache.get<{
      userId: string;
      rememberMe: boolean;
    }>(`refresh:${dto.refresh_token}`);

    if (!stored) {
      this.security.logSecurityEvent('INVALID_REFRESH_TOKEN', undefined, ip);
      handleException(
        APP_ERROR_CODE.UNAUTHORIZED,
        'Refresh token invalid or expired',
      );
    }

    // Rotate: delete used refresh token
    await this.cache.del(`refresh:${dto.refresh_token}`);

    const user = await this.prisma.user.findUnique({
      where: { id: stored.userId },
    });
    if (!user) {
      handleException(APP_ERROR_CODE.UNAUTHORIZED, 'User not found');
    }

    const refreshTtlSec = stored.rememberMe ? 60 * 60 * 24 * 30 : 60 * 60 * 2;
    return this.generateTokens(
      user.id,
      user.email,
      refreshTtlSec,
      stored.rememberMe,
      ip,
    );
  }

  /**
   * Logout: revoke refresh token + blacklist the current access token (if provided).
   * `authorization` is the raw value of the Authorization header (e.g., "Bearer xxx").
   */
  async logout(
    dto: RefreshTokenDto,
    authorization?: string,
    ip?: string,
  ): Promise<void> {
    // 1) Kill the refresh token (if present)
    if (dto.refresh_token) {
      await this.cache.del(`refresh:${dto.refresh_token}`);
    }

    // 2) Blacklist current access token (optional but recommended)
    const accessToken = this.extractBearer(authorization);
    if (accessToken) {
      const decoded = this.jwt.decode(accessToken);
      if (decoded.jti && decoded.exp) {
        const ttlMs = this.secondsUntil(decoded.exp) * 1000;
        if (ttlMs > 0) {
          await this.cache.set(`blacklist:${decoded.jti}`, true, ttlMs);
        }
      }
    }

    this.security.logSecurityEvent('LOGOUT', undefined, ip);
  }

  // --- helpers ---

  private extractBearer(authorization?: string): string | undefined {
    if (!authorization) return undefined;
    const [type, token] = authorization.split(' ');
    return type?.toLowerCase() === 'bearer' ? token : undefined;
  }

  private secondsUntil(expUnixSeconds: number): number {
    const now = Math.floor(Date.now() / 1000);
    return Math.max(0, expUnixSeconds - now);
  }
}
