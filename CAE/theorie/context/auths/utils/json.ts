import fs from "fs";
import path from "node:path";

/**
 * Parse items given in a .json file
 * @param {String} filePath - path to the .json file
 * If the file does not exist or it's content cannot be parsed as JSON data,
 * use the default data.
 * @param {Array} defaultArray - Content to be used when the .json file does not exists
 * @returns {Array} : the array that was parsed from the file (or defaultArray)
 */
function parse<T>(filePath: string, defaultArray: T[] = []): T[] {
  if (!fs.existsSync(filePath)) return defaultArray;
  const fileData = fs.readFileSync(filePath, "utf8");
  try {
    // parse() Throws a SyntaxError exception if the string to parse is not valid JSON.
    return JSON.parse(fileData) as T[];
  } catch (err) {
    return defaultArray;
  }
}

/**
 * Serialize the content of an Object within a file
 * @param {String} filePath - path to the .json file
 * @param {Array} object - Object to be written within the .json file.
 * Even if the file exists, its whole content is reset by the given object.
 */
function serialize(filePath: string, object: object) {
  const objectSerialized = JSON.stringify(object);
  createPotentialLastDirectory(filePath);
  fs.writeFileSync(filePath, objectSerialized);
}

/**
 *
 * @param {String} filePath - path to the .json file
 */
function createPotentialLastDirectory(filePath: string) {
  const pathToLastDirectory = filePath.substring(
    0,
    filePath.lastIndexOf(path.sep)
  );

  if (fs.existsSync(pathToLastDirectory)) return;

  fs.mkdirSync(pathToLastDirectory);
}

export { parse, serialize };
