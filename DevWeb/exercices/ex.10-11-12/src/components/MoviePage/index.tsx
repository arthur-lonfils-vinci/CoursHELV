import { Movie } from '../../types';
import { Box } from '@mui/material';
import { useParams } from 'react-router-dom';

interface MoviePageProps {
  movies: Movie[];
}

function MoviePage({ movies }: MoviePageProps) {

  const { id } = useParams<{ id: string }>();

  const idMovie = parseInt(id || "0");

  const movie = movies.find((movie) => movie.id === idMovie);

  console.log("ID Movie => " + idMovie);

  if (!movie) {
    return <div>Movie not found</div>;
  }

  return (
    <Box display="flex" width="90%" pt="3%">
      <Box flex="1" width="30%">
      <img src={movie.imageUrl} alt={movie.title} style={{ width: "100%" }} />
      </Box>
      <Box flex="2" pl="2%">
      <h1>{movie.title}</h1>
      <p>{movie.description}</p>
      <hr/>
      <p>Director: {movie.director}</p>
      <p>Duration: {movie.duration} minutes</p>
      <p>Budget: {movie.budget} million</p>
      </Box>
    </Box>
  );
}

export default MoviePage;