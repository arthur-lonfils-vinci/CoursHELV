import { IsNotEmpty, IsString, Matches, MinLength } from 'class-validator';
import { ApiProperty } from '@nestjs/swagger';

export class CreateUserDto {
  @IsString()
  @IsNotEmpty()
  @ApiProperty({
    description: 'User email address for creation',
    example: 'newuser@example.com',
    type: String,
    format: 'email',
  })
  email: string;

  @IsString()
  @IsNotEmpty()
  @ApiProperty({
    description: 'User firstname for creation',
    example: 'John',
    type: String,
  })
  firstName: string;

  @IsString()
  @IsNotEmpty()
  @ApiProperty({
    description: 'User lastname for creation',
    example: 'Doe',
    type: String,
  })
  lastName: string;

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
  passwordHash: string;

  @IsString()
  avatarUrl?: string;
}
