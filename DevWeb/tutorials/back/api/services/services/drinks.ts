import path from "node:path";
import { Drink, NewDrink } from "../types";
import { parse, serialize } from "../utils/json";
const jsonDbPath = path.join(__dirname, "/../data/drinks.json");

const drinks = parse(jsonDbPath) as Drink[];

function readAllDrinks(budgetMax: number): Drink[] {
  
  if (!budgetMax) {
    return drinks;
  }

  const budgetMaxNumber = Number(budgetMax);

  const filteredDrinks = drinks.filter((drink) => {
    return drink.price <= budgetMaxNumber;
  });
  return filteredDrinks;
}

function readOneDrink(id: number): Drink | undefined {
  const drink = drinks.find((drink) => drink.id === id);
  if (!drink) {
    return undefined;
  }
  return drink;
}

function createOneDrink(newDrink: NewDrink): Drink {

  const nextId =
    drinks.reduce((maxId, drink) => (drink.id > maxId ? drink.id : maxId), 0) +
    1;

  const createdDrink = {
    id: nextId,
    ...newDrink,
  };

  drinks.push(createdDrink);
  serialize(jsonDbPath, drinks);

  return createdDrink;
}

function deleteOneDrink(drinkId: number): Drink | undefined {
  const index = drinks.findIndex((drink) => drink.id === drinkId);
  if (index === -1) {
    return undefined;
  }

  const deletedElements = drinks.splice(index, 1);
  serialize(jsonDbPath, drinks);
  return deletedElements[0];
}

function updateOneDrink(
  drinkId: number,
  newDrink: Partial<NewDrink>
): Drink | undefined {
  
  const drink = drinks.find((drink) => drink.id === drinkId);
  if (!drink) {
    return undefined;
  }

  if (newDrink.title !== undefined) {
    drink.title = newDrink.title!; // the router already checks for the presence of title
  }
  if (newDrink.image !== undefined) {
    drink.image = newDrink.image!;
  }
  if (newDrink.volume !== undefined) {
    drink.volume = newDrink.volume!;
  }
  if (newDrink.price !== undefined) {
    drink.price = newDrink.price!;
  }

  serialize(jsonDbPath, drinks);
  return drink;
}

export {
  readAllDrinks,
  readOneDrink,
  createOneDrink,
  deleteOneDrink,
  updateOneDrink,
};
