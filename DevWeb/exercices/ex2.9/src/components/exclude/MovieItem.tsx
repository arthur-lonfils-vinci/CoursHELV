import { useState } from "react";

interface Movie {
  title: string;
  director: string;
  description?: string;
}

interface MovieItemProps {
  movie: Movie;
}

const MovieItem = ({ movie }: MovieItemProps) => {
  const [descriptionVisible, setDescriptionVisible] = useState(false);
  return (
    <li onClick={() => setDescriptionVisible(!descriptionVisible)}>
      <p>
        <strong>{movie.title}</strong> - Réalisateur : {movie.director}
      </p>
      <p>{descriptionVisible ? <i>{movie.description}</i> : null}</p>
    </li>
  );
};

export default MovieItem;

export type { Movie };
