interface Movie {
  id: number;
  title: string;
  director: string;
  duration: number;
  description?: string;
  imageLink?: string;
  budget?: number;
};

export type { Movie };