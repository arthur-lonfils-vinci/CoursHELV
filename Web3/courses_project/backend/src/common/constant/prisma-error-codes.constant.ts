export const PRISMA_ERROR_CODES = {
  /** Record to update or delete does not exist */
  RECORD_NOT_FOUND: 'P2025',

  /** Unique constraint failed */
  UNIQUE_CONSTRAINT_FAILED: 'P2002',

  /** Foreign key constraint failed */
  FOREIGN_KEY_CONSTRAINT_FAILED: 'P2003',

  /** Attempt to write NULL into a non-nullable field */
  NULL_CONSTRAINT_VIOLATION: 'P2004',

  /** Value invalid for field */
  INVALID_VALUE_FOR_FIELD: 'P2007',

  /** Query validation error */
  QUERY_VALIDATION_ERROR: 'P2011',

  /** Records for relation are required but missing */
  REQUIRED_RELATION_MISSING: 'P2012',

  /** Missing a required value */
  MISSING_REQUIRED_VALUE: 'P2013',

  /** The provided value for the column is too long */
  VALUE_TOO_LONG_FOR_COLUMN: 'P2014',

  /** Query interpretation error */
  QUERY_INTERPRETATION_ERROR: 'P2016',

  /** The relation filter on a relation is invalid */
  INVALID_RELATION_FILTER: 'P2017',

  /** The provided value for the enum is not valid */
  ENUM_VALUE_NOT_VALID: 'P2018',

  /** Raw query failed */
  RAW_QUERY_FAILED: 'P2019',

  /** The constraint failed (e.g., check constraint) */
  CONSTRAINT_FAILED: 'P2020',

  /** Input error */
  INPUT_ERROR: 'P2021',

  /** Value out of range for type */
  VALUE_OUT_OF_RANGE_FOR_TYPE: 'P2022',

  /** The query did not return a result */
  QUERY_RETURNED_NO_RESULT: 'P2024',

  /** The query is attempting to delete records that do not exist */
  DELETE_RECORD_DOES_NOT_EXIST: 'P2026',

  /** Transaction failed */
  TRANSACTION_FAILED: 'P2027',

  /** The database version is not supported */
  DATABASE_VERSION_NOT_SUPPORTED: 'P1022',

  /** Schema validation failed */
  SCHEMA_VALIDATION_ERROR: 'P1012',
} as const;

export type PrismaErrorCode =
  (typeof PRISMA_ERROR_CODES)[keyof typeof PRISMA_ERROR_CODES];
