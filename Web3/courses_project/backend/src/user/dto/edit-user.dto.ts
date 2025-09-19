import { ApiProperty } from '@nestjs/swagger';
import { IsEmail, IsOptional, IsString } from 'class-validator';

export class EditUserDto {
  @IsEmail()
  @IsOptional()
  @ApiProperty({
    description: 'User email address',
    example: 'updated@example.com',
    type: String,
    format: 'email',
    required: false,
  })
  email?: string;

  @IsString()
  @IsOptional()
  @ApiProperty({
    description: 'User first name',
    example: 'John',
    type: String,
    required: false,
  })
  firstname?: string;

  @IsString()
  @IsOptional()
  @ApiProperty({
    description: 'User last name',
    example: 'Doe',
    type: String,
    required: false,
  })
  lastname?: string;
}
