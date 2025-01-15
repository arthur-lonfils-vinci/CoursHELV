import { Request } from "express";

// NEW INTERFACES AND TYPES FOR THE PROJECT

export interface Ticket {
  id: number;
  title: string;
  description: string;
  creator: string;
  creationDate: string;
}

export type NewTicket = Omit<Ticket, "id">;




// INTERFACES AND TYPES ALREADY DEFINED IN THE PROJECT
interface Pizza {
  id: number;
  title: string;
  content: string;
}

interface PizzaToUpdate {
  title?: string;
  content?: string;
}

type NewPizza = Omit<Pizza, "id">;

interface Drink {
  id: number;
  title: string;
  image: string;
  volume: number;
  price: number;
}

type NewDrink = Omit<Drink, "id">;

interface AuthenticatedUser {
  username: string;
  token: string;
}

interface User {
  id: number;
  username: string;
  password: string;
}

type PotentialUser = Omit<User, "id">;

interface AuthenticatedRequest extends Request {
  user?: User;
}

interface JwtPayload {
  username: string;
  exp: number; // Expiration time (in seconds since the epoch)
  iat: number; // Issued at time (in seconds since the epoch)
}


export type {
  Pizza,
  NewPizza,
  PizzaToUpdate,
  Drink,
  NewDrink,
  AuthenticatedUser,
  User,
  PotentialUser,
  AuthenticatedRequest,
  JwtPayload,
};
