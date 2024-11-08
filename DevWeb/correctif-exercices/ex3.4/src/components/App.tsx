import { Outlet, useNavigate } from "react-router-dom";
import "./App.css";
import Footer from "./Footer";
import Header from "./Header";
import NavBar from "./Navbar";
import { useEffect, useState } from "react";
import {
  AuthenticatedUser,
  MaybeAuthenticatedUser,
  Movie,
  MovieContext,
  NewMovie,
  User,
} from "../types";
import { addMovie, deleteMovie, fetchMovies } from "../utils/film-service";
import {
  clearAuthenticatedUser,
  getAuthenticatedUser,
  storeAuthenticatedUser,
} from "../utils/session";

const App = () => {
  const currentTheme = localStorage.getItem("theme") ?? "dark";
  const [movies, setMovies] = useState<Movie[]>([]);
  const [theme, setTheme] = useState<"light" | "dark">(
    currentTheme as "light" | "dark"
  );
  const [authenticatedUser, setAuthenticatedUser] =
    useState<MaybeAuthenticatedUser>(undefined);
  const navigate = useNavigate();

  const initMovies = async () => {
    try {
      const movies = await fetchMovies();
      setMovies(movies);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    initMovies();
    const authenticatedUser = getAuthenticatedUser();
    if (authenticatedUser) {
      setAuthenticatedUser(authenticatedUser);
    }
  }, []);

  const onMovieAdded = async (newMovie: NewMovie) => {
    console.log("Movie to add:", newMovie);
    try {
      if (!authenticatedUser) {
        throw new Error("User is not authenticated");
      }
      const movieToBeAdded = await addMovie(newMovie, authenticatedUser);
      console.log("Movie added:", movieToBeAdded);
      await initMovies();
      navigate("/movie-list");
    } catch (error) {
      console.error(error);
    }
  };

  const onMovieDeleted = async (movie: Movie) => {
    console.log("Movie to delete:", movie);

    try {
      if (!authenticatedUser) {
        throw new Error("User is not authenticated");
      }
      await deleteMovie(movie, authenticatedUser);
      console.log("Movie deleted:", movie);
      await initMovies();
    } catch (error) {
      console.error(error);
    }
  };

  const registerUser = async (newUser: User) => {
    try {
      const options = {
        method: "POST",
        body: JSON.stringify(newUser),
        headers: {
          "Content-Type": "application/json",
        },
      };

      const response = await fetch("/api/auths/register", options);

      if (!response.ok)
        throw new Error(
          `fetch error : ${response.status} : ${response.statusText}`
        );

      const createdUser: AuthenticatedUser = await response.json();

      setAuthenticatedUser(createdUser);
      storeAuthenticatedUser(createdUser);

      console.log("createdUser: ", createdUser);
    } catch (err) {
      console.error("registerUser::error: ", err);
      throw err;
    }
  };

  const loginUser = async (user: User) => {
    try {
      const options = {
        method: "POST",
        body: JSON.stringify(user),
        headers: {
          "Content-Type": "application/json",
        },
      };

      const response = await fetch("/api/auths/login", options);

      if (!response.ok)
        throw new Error(
          `fetch error : ${response.status} : ${response.statusText}`
        );

      const authenticatedUser: AuthenticatedUser = await response.json();
      console.log("authenticatedUser: ", authenticatedUser);

      setAuthenticatedUser(authenticatedUser);
      storeAuthenticatedUser(authenticatedUser);
    } catch (err) {
      console.error("loginUser::error: ", err);
      throw err;
    }
  };

  const clearUser = () => {
    clearAuthenticatedUser();
    setAuthenticatedUser(undefined);
  };

  const movieContext: MovieContext = {
    movies,
    onMovieAdded,
    onMovieDeleted,
    registerUser,
    loginUser,
    authenticatedUser,
  };

  const handleThemeChange = () => {
    const newTheme = theme === "dark" ? "light" : "dark";
    setTheme(newTheme);
    localStorage.setItem("theme", newTheme);
  };

  return (
    <div>
      <Header
        urlLogo="https://media.istockphoto.com/id/1429764305/fr/vectoriel/bande-de-film-vierge-isol%C3%A9e-sur-le-fond-blanc.jpg?s=1024x1024&w=is&k=20&c=is5Y6cun0NC8PxJd51p4YnUoLUpyb758Bdigh4Bqn48="
        theme={theme}
      >
        <h1>Tous sur les films</h1>
        <NavBar authenticatedUser={authenticatedUser} clearUser={clearUser} />
      </Header>

      <main className="page-content">
        <Outlet context={movieContext} />
      </main>

      <Footer
        urlLogo="https://media.istockphoto.com/id/1202770152/fr/photo/bobine-de-film-disolement-sur-le-fond-jaune-lumineux-dans-les-couleurs-pastel.jpg?s=1024x1024&w=is&k=20&c=2yKBrC8oyimPdW-5IxFWN_zxFPVK3KWYL9OE2gVmVX4="
        theme={theme}
        handleThemeChange={handleThemeChange}
      >
        <p>© myMovies</p>
      </Footer>
    </div>
  );
};

export default App;
