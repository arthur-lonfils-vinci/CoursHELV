import {
  NotFoundException,
  ConflictException,
  BadRequestException,
  InternalServerErrorException,
} from '@nestjs/common';
import { PRISMA_ERROR_CODES } from '../constant';

export function handlePrismaError(code: string, msg?: string): never {
  switch (code) {
    case PRISMA_ERROR_CODES.RECORD_NOT_FOUND: // P2025
      throw new NotFoundException(msg || 'Record not found');

    case PRISMA_ERROR_CODES.UNIQUE_CONSTRAINT_FAILED: // P2002
      throw new ConflictException(
        msg || 'Duplicate entry violates unique constraint',
      );

    case PRISMA_ERROR_CODES.FOREIGN_KEY_CONSTRAINT_FAILED: // P2003
      throw new ConflictException(
        msg || 'Foreign key constraint failed: related record not found',
      );

    case PRISMA_ERROR_CODES.NULL_CONSTRAINT_VIOLATION: // P2004
      throw new BadRequestException(
        msg || 'Null value violates non-null constraint',
      );

    case PRISMA_ERROR_CODES.INVALID_VALUE_FOR_FIELD: // P2007
      throw new BadRequestException(msg || 'Invalid value provided for field');

    case PRISMA_ERROR_CODES.QUERY_VALIDATION_ERROR: // P2011
      throw new BadRequestException(msg || 'Query validation error');

    case PRISMA_ERROR_CODES.REQUIRED_RELATION_MISSING: // P2012
      throw new BadRequestException(msg || 'Missing required related records');

    case PRISMA_ERROR_CODES.MISSING_REQUIRED_VALUE: // P2013
      throw new BadRequestException(msg || 'Missing a required value');

    case PRISMA_ERROR_CODES.VALUE_TOO_LONG_FOR_COLUMN: // P2014
      throw new BadRequestException(msg || 'Value too long for column');

    case PRISMA_ERROR_CODES.QUERY_INTERPRETATION_ERROR: // P2016
      throw new BadRequestException(msg || 'Query interpretation error');

    case PRISMA_ERROR_CODES.INVALID_RELATION_FILTER: // P2017
      throw new BadRequestException(msg || 'Invalid relation filter');

    case PRISMA_ERROR_CODES.ENUM_VALUE_NOT_VALID: // P2018
      throw new BadRequestException(msg || 'Invalid enum value provided');

    case PRISMA_ERROR_CODES.RAW_QUERY_FAILED: // P2019
      throw new InternalServerErrorException(msg || 'Raw query failed');

    case PRISMA_ERROR_CODES.CONSTRAINT_FAILED: // P2020
      throw new BadRequestException(msg || 'Database constraint failed');

    case PRISMA_ERROR_CODES.INPUT_ERROR: // P2021
      throw new BadRequestException(msg || 'Input error');

    case PRISMA_ERROR_CODES.VALUE_OUT_OF_RANGE_FOR_TYPE: // P2022
      throw new BadRequestException(msg || 'Value out of range for type');

    case PRISMA_ERROR_CODES.QUERY_RETURNED_NO_RESULT: // P2024
      throw new NotFoundException(msg || 'Query returned no results');

    case PRISMA_ERROR_CODES.DELETE_RECORD_DOES_NOT_EXIST: // P2026
      throw new NotFoundException(
        msg || 'Attempt to delete non-existent record',
      );

    case PRISMA_ERROR_CODES.TRANSACTION_FAILED: // P2027
      throw new InternalServerErrorException(
        msg || 'Database transaction failed',
      );

    case PRISMA_ERROR_CODES.DATABASE_VERSION_NOT_SUPPORTED: // P1022
      throw new InternalServerErrorException(
        msg || 'Database version not supported',
      );

    case PRISMA_ERROR_CODES.SCHEMA_VALIDATION_ERROR: // P1012
      throw new InternalServerErrorException(msg || 'Schema validation failed');

    default:
      // For unknown codes, throw a generic error including code and message
      throw new InternalServerErrorException(
        msg || `Unexpected database error (code: ${code})`,
      );
  }
}
