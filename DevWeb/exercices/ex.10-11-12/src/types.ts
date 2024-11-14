interface MovieFavourite {
  title: string;
  director: string;
  duration: number;
  imageUrl?: string;
  description?: string;
  budget?: number;
}
interface MovieList {
  title: string;
  director: string;
  description?: string;
}

export type { MovieFavourite as Movie, MovieList };