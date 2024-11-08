interface Film {
  id: number;
  title: string;
  director: string;
  duration: number;
  budget?: number;
  description?: URL;
  imageURL?: URL;
}

type NewFilm = Omit<Film, "id">;

export type { Film, NewFilm};
