import { IsBoolean, IsEmail, IsNotEmpty, IsString } from 'class-validator';
import { ApiProperty } from '@nestjs/swagger';

export class LoginDto {
  @IsEmail()
  @IsNotEmpty()
  @ApiProperty({
    description: 'User email address',
    example: 'user@example.com',
    type: String,
    format: 'email',
  })
  email: string;

  @IsString()
  @IsNotEmpty()
  @ApiProperty({
    description: 'User password',
    example: 'SecurePassword123!',
    type: String,
    minLength: 8,
  })
  password: string;

  @IsBoolean()
  @IsNotEmpty()
  @ApiProperty({
    description: 'Whether to remember the user session',
    example: true,
    type: Boolean,
    default: false,
  })
  rememberMe: boolean;
}
