import { Link } from "react-router-dom";
import { Movie } from "../types";

interface MovieTitleListProps {
  movies: Movie[];
}

const MovieTitleList = ({ movies }: MovieTitleListProps) => {
  return (
    <ul>
      {movies.map((movie) => (
        <li key={movie.id}>
          <Link to={`/movies/${movie.id}`}>{movie.title}</Link>{" "}
        </li>
      ))}
    </ul>
  );
};

export default MovieTitleList;
