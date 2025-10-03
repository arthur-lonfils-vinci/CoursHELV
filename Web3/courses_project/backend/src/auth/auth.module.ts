import { Module } from '@nestjs/common';
import { AuthService } from './auth.service';
import { AuthController } from './auth.controller';
import { JwtModule } from '@nestjs/jwt';
import { SecurityService } from '../security/security.service';
import { GoogleStrategy } from './strategies/google.strategies';
import googleOauthConfig from 'src/config/google-oauth.config';
import { ConfigModule } from '@nestjs/config';
import { UserService } from 'src/user/user.service';

@Module({
  imports: [
    JwtModule.register({
      global: true,
      secret: process.env.JWT_SECRET,
      signOptions: {
        expiresIn: '15m',
      },
    }),
    ConfigModule.forFeature(googleOauthConfig),
  ],
  controllers: [AuthController],
  providers: [AuthService, SecurityService, GoogleStrategy, UserService],
})
export class AuthModule {}
