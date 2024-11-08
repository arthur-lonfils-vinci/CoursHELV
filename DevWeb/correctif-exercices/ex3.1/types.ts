import { Request } from "express";

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

interface Film {
  id: number;
  title: string;
  director: string;
  duration: number;
  budget?: number;
  description?: string;
  imageUrl?: string;
}

interface Comment {
  filmId: number;
  username: string;
  comment: string;
}

type NewFilm = Omit<Film, "id">;

export type {
  AuthenticatedUser,
  User,
  PotentialUser,
  AuthenticatedRequest,
  JwtPayload,
  Film,
  NewFilm,
  Comment,
};
