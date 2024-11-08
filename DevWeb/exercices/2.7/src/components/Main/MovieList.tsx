import { Movie } from "../../types";
import "./MovieList.css";

interface MovieListProps {
  movies: Movie[];
}

const MovieList = ({ movies }: MovieListProps) => {
  return (
    <table>
      <thead>
      <tr>
        <th>Title</th>
        <th>Director</th>
        <th>Duration</th>
        <th>Image</th>
        <th>Budget</th>
        <th>Description</th>
      </tr>
      </thead>
      <tbody>
      {movies.map((movie) => (
        <tr key={movie.id}>
        <td>{movie.title}</td>
        <td>{movie.director}</td>
        <td>{movie.duration} minutes</td>
        <td>{movie.imageLink && <img src={movie.imageLink} alt={movie.title} style={{ width: '100px' }} />}</td>
        <td>{movie.budget ? `$${movie.budget}` : 'N/A'}</td>
        <td>{movie.description}</td>
        </tr>
      ))}
      </tbody>
    </table>
  );
};

export default MovieList;
