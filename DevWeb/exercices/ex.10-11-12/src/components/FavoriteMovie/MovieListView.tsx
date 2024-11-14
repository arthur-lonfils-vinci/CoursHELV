import MovieCard from "./MovieCard";
import { Movie } from "../../types";
import { Container } from "@mui/material";
import Grid from "@mui/material/Grid"; // Import Grid from the stable module
import AddMovieForm from "./AddMovieForm";

interface MovieListViewProps {
  movies: Movie[];
  onMovieAdded: (movie: Movie) => void;
}

const MovieListView = ({ movies, onMovieAdded }: MovieListViewProps) => {
  return (
    <Container>
      <Grid container spacing={4}>
        {movies.map((movie) => (
          <Grid item key={movie.title} xs={12} sm={6} md={4}>
            <MovieCard movie={movie} />
          </Grid>
        ))}
        <Grid item xs={12} sm={6} md={4}>
          <AddMovieForm onMovieAdded={onMovieAdded}/>
        </Grid>
      </Grid>
    </Container>
  );
};

export default MovieListView;
