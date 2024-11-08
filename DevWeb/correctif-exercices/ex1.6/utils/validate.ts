/**
 * Returns true if the object contains only keys from the expectedKeys array
 * @param obj
 * @param expectedKeys
 */
const containsOnlyExpectedKeys = (
  obj: object,
  expectedKeys: string[]
): boolean => {
  return Object.keys(obj).every((key) => expectedKeys.includes(key));
};

export { containsOnlyExpectedKeys };
