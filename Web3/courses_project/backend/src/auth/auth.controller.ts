import {
  Body,
  Controller,
  HttpCode,
  HttpStatus,
  Ip,
  Post,
  Headers,
} from '@nestjs/common';
import { ApiOperation } from '@nestjs/swagger';
import { AuthService } from './auth.service';
import { LoginDto, RegisterDto, RefreshTokenDto } from './dto';
import { SafeUser } from '../common/types';

@Controller('auth')
export class AuthController {
  constructor(private authService: AuthService) {}

  @Post('register')
  @ApiOperation({
    summary: 'Register a new user',
    description: 'Create a new user account with email, password and role',
  })
  register(@Body() dto: RegisterDto): Promise<SafeUser> {
    return this.authService.register(dto);
  }

  @HttpCode(HttpStatus.OK)
  @Post('login')
  @ApiOperation({
    summary: 'User login',
    description: 'Authenticate user and return access and refresh tokens',
  })
  login(
    @Body() dto: LoginDto,
    @Ip() ip: string,
  ): Promise<{ access_token: string; refresh_token: string }> {
    return this.authService.login(dto, ip);
  }

  @HttpCode(HttpStatus.OK)
  @Post('refresh')
  @ApiOperation({
    summary: 'Refresh access token',
    description: 'Generate new access token using refresh token',
  })
  async refresh(
    @Body() dto: RefreshTokenDto,
    @Ip() ip: string,
  ): Promise<{ access_token: string; refresh_token: string }> {
    return this.authService.refreshToken(dto, ip);
  }

  @HttpCode(HttpStatus.OK)
  @Post('logout')
  @ApiOperation({
    summary: 'User logout',
    description: 'Invalidate user session and tokens',
  })
  async logout(
    @Body() dto: RefreshTokenDto,
    @Ip() ip: string,
    @Headers('Authorization') authorization: string,
  ): Promise<{ message: string }> {
    await this.authService.logout(dto, authorization, ip);
    return { message: 'Logged out successfully' };
  }
}
