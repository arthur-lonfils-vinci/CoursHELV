import MovieItem from "./MovieItem";

interface Movie {
  title: string;
  director: string;
  description?: string;
}

interface CinemaProps {
  name: string;
  movies: Movie[];
}

const Cinema = (props: CinemaProps) => (
  <div>
    <h2>{props.name}</h2>
    <ul>
      {props.movies.map((movie) => (
        <MovieItem key={movie.title} movie={movie} />
      ))}
    </ul>
  </div>
);

export default Cinema;
