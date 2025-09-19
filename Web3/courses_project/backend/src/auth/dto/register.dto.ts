import { IsNotEmpty, IsString, Matches, MinLength } from 'class-validator';
import { ApiProperty } from '@nestjs/swagger';

export class RegisterDto {
  @IsString()
  @IsNotEmpty()
  @ApiProperty({
    description: 'User email address for registration',
    example: 'newuser@example.com',
    type: String,
    format: 'email',
  })
  email: string;

  @IsString()
  @IsNotEmpty()
  @ApiProperty({
    description: 'User firstname for registration',
    example: 'John',
    type: String,
  })
  firstname: string;

  @IsString()
  @IsNotEmpty()
  @ApiProperty({
    description: 'User lastname for registration',
    example: 'Doe',
    type: String,
  })
  lastname: string;

  @IsString()
  @IsNotEmpty()
  @MinLength(10)
  @Matches(
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/,
    {
      message:
        'Password must contain uppercase, lowercase, number and special character',
    },
  )
  @ApiProperty({
    description:
      'User password - must contain uppercase, lowercase, number and special character',
    example: 'SecurePassword123!',
    type: String,
    minLength: 10,
    pattern:
      '^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$',
  })
  password: string;
}
