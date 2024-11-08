import MovieCard from "./MovieCard";
import { Movie } from "../types";
import "./MovieListView.css";

interface MovieListViewProps {
  movies: Movie[];
  onMovieDeleted?: (movie: Movie) => void;
  handleEditMovieRequest?: (movie: Movie) => void;
}

const MovieListView = ({
  movies,
  onMovieDeleted,
  handleEditMovieRequest,
}: MovieListViewProps) => {
  return (
    <div>
      <ul className="movie-list-view">
        {movies.map((movie) => (
          <MovieCard
            key={movie.title}
            movie={movie}
            onMovieDeleted={onMovieDeleted}
            handleEditMovieRequest={handleEditMovieRequest}
          />
        ))}
      </ul>
    </div>
  );
};

export default MovieListView;
