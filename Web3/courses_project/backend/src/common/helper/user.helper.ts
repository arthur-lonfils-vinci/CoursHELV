import { User } from '@prisma/client';
import { exclude } from './exclude.helper';
import { SafeUser } from '../types';

/**
 * Removes the password hash from a User object.
 *
 * @param user - The full User object from the database.
 * @returns A safeUser object without the hash field.
 */
export const excludeUserHash = (user: User): SafeUser =>
  exclude(user, ['passwordHash']) as SafeUser;
