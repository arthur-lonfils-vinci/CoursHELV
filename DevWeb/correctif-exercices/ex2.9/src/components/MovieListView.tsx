import MovieCard from "./MovieCard";
import { Movie } from "../types";
import { Box } from "@mui/material";

interface MovieListViewProps {
  movies: Movie[];
}

const MovieListView = ({ movies }: MovieListViewProps) => {
  return (
    <Box
      display="flex"
      sx={{ flexDirection: "row", flexWrap: "wrap", gap: "1rem" }}
    >
      {movies.map((movie) => (
        <MovieCard key={movie.title} movie={movie} />
      ))}
    </Box>
  );
};

export default MovieListView;
