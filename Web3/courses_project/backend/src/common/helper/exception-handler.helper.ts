import {
  BadRequestException,
  UnauthorizedException,
  ForbiddenException,
  NotFoundException,
  InternalServerErrorException,
  ConflictException,
} from '@nestjs/common';
import { APP_ERROR_CODE } from '../constant';
import { handlePrismaError } from './prisma-exception.helper';
import { PrismaClientKnownRequestError } from '@prisma/client/runtime/library';

export function handleException(error: unknown, msg?: string): never {
  console.log('Handling exception:', error);
  if (error instanceof PrismaClientKnownRequestError) {
    console.log('Handling known Prisma error:');
    handlePrismaError(error.code, msg);
  }

  if (typeof error === 'string' && error.startsWith('P')) {
    console.log('Handling manual Prisma error:');
    return handlePrismaError(error, msg);
  }

  console.log('Handling App error:');

  // Handle known NestJS HTTP errors
  if (error instanceof BadRequestException) throw error;
  if (error instanceof UnauthorizedException) throw error;
  if (error instanceof ForbiddenException) throw error;
  if (error instanceof NotFoundException) throw error;

  // Map generic errors to HTTP
  switch (error) {
    case APP_ERROR_CODE.UNAUTHORIZED:
      throw new UnauthorizedException(msg || 'Unauthorized');
    case APP_ERROR_CODE.FORBIDDEN:
      throw new ForbiddenException(msg || 'Forbidden');
    case APP_ERROR_CODE.BAD_REQUEST:
      throw new BadRequestException(msg || 'Bad request');
    case APP_ERROR_CODE.NOT_FOUND:
      throw new NotFoundException(msg || 'Not found');
    case APP_ERROR_CODE.CONFLICT:
      throw new ConflictException(msg || 'Conflict');
    default:
      throw new InternalServerErrorException(
        // eslint-disable-next-line @typescript-eslint/restrict-template-expressions
        msg || `Internal server error: ${error}`,
      );
  }
}
