// Create the pizzas service based on the drinks.ts service
import path from "node:path";
import { Pizza, NewPizza } from "../types";
import { parse, serialize } from "../utils/json";
const jsonDbPath = path.join(__dirname, "/../data/pizzas.json");

const pizzas = parse(jsonDbPath) as Pizza[]; 

function readAllPizzas(order: string | undefined): Pizza[] {
  const orderByTitle = order && order.includes("title") ? order : undefined;

  let orderedMenu: Pizza[] = [];
  if (orderByTitle)
    orderedMenu = [...pizzas].sort((a, b) => a.title.localeCompare(b.title));

  if (orderByTitle === "-title") orderedMenu = orderedMenu.reverse();

  return orderedMenu.length === 0 ? pizzas : orderedMenu;
}

function readPizzaById(id: number): Pizza | undefined {
  return pizzas.find((pizza) => pizza.id === id);
}

function createPizza(newPizza: NewPizza): Pizza {
  const lastId = pizzas[pizzas.length - 1].id;
  const pizza: Pizza = { id: lastId + 1, ...newPizza };
  const updatedPizzas = [...pizzas, pizza];
  serialize(jsonDbPath, updatedPizzas);
  return pizza;
}

function deletePizza(id: number): Pizza | undefined {
  const index = pizzas.findIndex((pizza) => pizza.id === id);
  if (index === -1) return undefined;

  const deletedElements = pizzas.splice(index, 1);
  serialize(jsonDbPath, pizzas);
  return deletedElements[0];
}

function updatePizza(
  id: number,
  updatedPizza: Partial<NewPizza>
): Pizza | undefined {
  const pizza = pizzas.find((pizza) => pizza.id === id);
  if (!pizza) return undefined;

  if (updatedPizza.title !== undefined) {
    pizza.title = updatedPizza.title;
  }
  if (updatedPizza.content !== undefined) {
    pizza.content = updatedPizza.content;
  }

  serialize(jsonDbPath, pizzas);
  return pizza;
}

export { readAllPizzas, readPizzaById, createPizza, deletePizza, updatePizza };
