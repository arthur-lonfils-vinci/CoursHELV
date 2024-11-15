import CssBaseline from "@mui/material/CssBaseline";
import "@fontsource/roboto/700.css";
import { ThemeProvider } from "@mui/material/styles";
import React from "react";
import ReactDOM from "react-dom/client";
import App from "./components/App";
import theme from './themes.ts'
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import FavoriteMovie from "./components/FavoriteMovie";
import HomePage from "./components/Main";
import CinemaPage from "./components/CinemaPage";
import { Movie } from "./types";
import MoviePage from "./components/MoviePage";

const defaultMovies: Movie[] = [
  {
    id: 1,
    title: "Shang-Chi and the Legend of the Ten Rings",
    director: "Destin Daniel Cretton",
    duration: 132,
    imageUrl: "https://www.themoviedb.org/t/p/original/9f2Q0U3IOsLgrI2HkvldwSABZy5.jpg",
    description: `The story begins with Xu Shang-Chi, who goes by "Shaun", living a quiet life in San Francisco. He works as a valet alongside his best friend, Katy.
                  His unassuming life takes a turn when a group of assassins attacks him on a city bus, seeking a pendant his mother gave him. During this intense and beautifully choreographed fight, 
                  Shang-Chi reveals his extraordinary martial arts skills, shocking Katy, who was unaware of his past.
                  The attack leads Shang-Chi to reveal his true identity to Katy: he is the son of Xu Wenwu, the leader of the Ten Rings organization, a powerful and ancient criminal empire. 
                  Wenwu wields the legendary Ten Rings, mystical artifacts that grant him immense power and immortality. Shang-Chi fled his father's oppressive and ruthless training as a teenager, choosing to build a new life in the United States.
                  Wenwu, believing that Shang-Chi's mother, Li, is alive and being held captive in her mystical homeland of Ta Lo, summons Shang-Chi and his estranged sister, Xu Xialing. 
                  Xialing runs an underground fight club and harbors resentment toward her family for being overlooked in her father's legacy. 
                  Despite her anger, she joins Shang-Chi and Katy in their quest to stop Wenwu's potentially disastrous mission.`,
    budget: 150,
  },
  {
    id: 2,
    title: "The Matrix",
    director: "Lana Wachowski, Lilly Wachowski",
    duration: 136,
    imageUrl: "https://picfiles.alphacoders.com/385/385304.jpg",
    description:
      "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
    budget: 63,
  },
  {
    id: 3,
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
    id: 4,
    title: "The Meyerowitz Stories",
    director: "Noah Baumbach",
    duration: 112,
    imageUrl:
      "https://cdn.traileraddict.com/content/netflix/the-meyerowitz-stories-poster.jpg",
    description:
      "An estranged family gathers together in New York City for an event celebrating the artistic work of their father.",
  },
  {
    id: 5,
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


const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "",
        element: <HomePage defaultMovies={defaultMovies} />,
      },
      {
        path: "moviespage",
        element: <FavoriteMovie defaultMovies={defaultMovies} />,
      },
      {
        path: "cinemaspage",
        element: <CinemaPage />,
      },
      {
        path: `moviepage/:id`,
        element: <MoviePage movies={defaultMovies} />
      }
    ],
  },
]);

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <ThemeProvider theme={theme.dark}>
      <CssBaseline />
      <RouterProvider router={router} />
    </ThemeProvider>
  </React.StrictMode>
);
