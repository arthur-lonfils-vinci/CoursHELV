/**
 * Returns a shallow copy of the given object with the specified keys excluded.
 *
 * This utility is useful when you want to remove sensitive or unnecessary fields
 * from an object before returning or storing it (e.g., removing a password hash
 * from a user object).
 *
 * @template T - The type of the original object.
 * @template K - The keys to exclude from the object.
 * @param obj - The original object.
 * @param keys - An array of keys to remove from the returned object.
 * @returns A new object with the specified keys excluded.
 *
 * @example
 * const user = {
 *   id: 1,
 *   email: 'user@example.com',
 *   password: 'hashed_password',
 * };
 *
 * const safeUser = exclude(user, ['password']);
 * // Result: { id: 1, email: 'user@example.com' }
 */
export function exclude<T extends object, K extends keyof T>(
  obj: T,
  keys: K[],
): Omit<T, K> {
  const result = { ...obj };
  for (const key of keys) {
    delete result[key];
  }
  return result;
}
