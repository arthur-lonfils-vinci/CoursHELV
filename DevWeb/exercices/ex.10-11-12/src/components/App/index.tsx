import { Box, Typography, useTheme } from "@mui/material";
import { Copyright } from "@mui/icons-material";
import { useState } from "react";
import Footer from "../Footer";
import Header from "../Header";
import MovieListView from "../Main/MovieListView";
import PageTitle from "../Main/PageTitle";
import { Movie } from "../../types";

const defaultMovies: Movie[] = [
  {
    title: "Shang-Chi and the Legend of the Ten Rings",
    director: "Destin Daniel Cretton",
    duration: 132,
    imageUrl:
      "https://www.themoviedb.org/t/p/original/9f2Q0U3IOsLgrI2HkvldwSABZy5.jpg",
    description:
      "Shang-Chi, the master of unarmed weaponry-based Kung Fu, is forced to confront his past after being drawn into the Ten Rings organization.",
    budget: 150,
  },
  {
    title: "The Matrix",
    director: "Lana Wachowski, Lilly Wachowski",
    duration: 136,
    imageUrl: "https://picfiles.alphacoders.com/385/385304.jpg",
    description:
      "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
    budget: 63,
  },
  {
    title: "Summer Wars",
    director: "Mamoru Hosoda",
    duration: 114,
    imageUrl:
      "https://m.media-amazon.com/images/M/MV5BMTYyOTk3OTQzM15BMl5BanBnXkFtZTcwMjU4NDYyNA@@._V1_.jpg",
    description:
      "A young math genius solves a complex equation and inadvertently puts a virtual world's artificial intelligence in a position to destroy Earth.",
    budget: 18.7,
  },
  {
    title: "The Meyerowitz Stories",
    director: "Noah Baumbach",
    duration: 112,
    imageUrl:
      "https://cdn.traileraddict.com/content/netflix/the-meyerowitz-stories-poster.jpg",
    description:
      "An estranged family gathers together in New York City for an event celebrating the artistic work of their father.",
  },
  {
    title: "Rogue One: A Star Wars Story",
    director: "Gareth Edwards",
    duration: 134,
    imageUrl:
      "https://image.tmdb.org/t/p/original/xQVBU9SWgI3e2doljPQshlUL6GR.jpg",
    description: `Galen Erso is forced by the Empire to help complete the Death Star, 
      a planet-destroying superweapon. His daughter, Jyn, escapes and is later recruited by the Rebel Alliance to learn more about her father’s work. 
      Joined by rebels Cassian Andor, Bodhi Rook, K-2SO, Chirrut Îmwe, and Baze Malbus, Jyn leads a daring mission to obtain critical Death Star information. 
      Their effort sparks hope and becomes a turning point for the Rebellion against the Empire.`,
    budget: 23,
  },
];

function App() {
  const theme = useTheme();

  const [movies, setMovies] = useState(defaultMovies);

  const onMovieAdded = (newMovie: Movie) => {
    console.log("Movie to add:", newMovie);
    setMovies([...movies, newMovie]);
  };

  return (
    <Box
      sx={{
        py: 2,
        display: "flex",
        flexDirection: "column",
        height: "100%",
        alignItems: "center",
        backgroundColor: theme.palette.background.default,
      }}
    >
      <Header urlLogo="https://logodix.com/logo/2098363.png" />

      <Box
        sx={{
          py: 4,
        }}
      >
        <PageTitle title="My favorite movies" />
        <MovieListView movies={movies} onMovieAdded={onMovieAdded} />
      </Box>

      <Footer urlLogo="https://media.istockphoto.com/id/1202770152/fr/photo/bobine-de-film-disolement-sur-le-fond-jaune-lumineux-dans-les-couleurs-pastel.jpg?s=1024x1024&w=is&k=20&c=2yKBrC8oyimPdW-5IxFWN_zxFPVK3KWYL9OE2gVmVX4=">
        <Typography>
          <Copyright />
          MesFilms
        </Typography>
      </Footer>
    </Box>
  );
}

export default App;
