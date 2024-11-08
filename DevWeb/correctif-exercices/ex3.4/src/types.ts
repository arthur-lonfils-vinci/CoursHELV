interface Movie {
  id: number;
  title: string;
  director: string;
  duration: number;
  imageUrl?: string;
  description?: string;
  budget?: number;
}

interface MovieContext {
  movies: Movie[];
  onMovieAdded: (newMovie: NewMovie) => void;
  onMovieDeleted: (movie: Movie) => void;
  registerUser: (user: User) => Promise<void>;
  loginUser: (user: User) => Promise<void>;
  authenticatedUser: MaybeAuthenticatedUser;
}

type NewMovie = Omit<Movie, "id">;

interface User {
  username: string;
  password: string;
}

interface AuthenticatedUser {
  username: string;
  token: string;
}

type MaybeAuthenticatedUser = AuthenticatedUser | undefined;

export type {
  Movie,
  MovieContext,
  NewMovie,
  User,
  AuthenticatedUser,
  MaybeAuthenticatedUser,
};
