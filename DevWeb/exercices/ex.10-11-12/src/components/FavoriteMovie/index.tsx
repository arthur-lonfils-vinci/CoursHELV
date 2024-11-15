import { Box } from "@mui/material";
import { useState } from "react";
import MovieListView from "../FavoriteMovie/MovieListView";
import PageTitle from "../FavoriteMovie/PageTitle";
import { Movie } from "../../types";

interface MovieProps {
  defaultMovies: Movie[];
}

function FavoriteMovie({ defaultMovies }: MovieProps) {

  const [movies, setMovies] = useState<Movie[]>(defaultMovies);

  const onMovieAdded = (newMovie: Movie) => {
    console.log("Movie to add:", newMovie);
    setMovies([...movies, newMovie]);
  };

  return (
      <Box
        sx={{
          py: 4,
        }}
      >
        <PageTitle title="My favorite movies" />
        <MovieListView movies={movies} onMovieAdded={onMovieAdded} />
      </Box>
  );
}

export default FavoriteMovie;
