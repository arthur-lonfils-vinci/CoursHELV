import { SyntheticEvent, useState } from "react";
import { Movie } from "../../types";
import MovieList from "./MovieList";
import "./index.css";

const defaultMovies: Movie[] = [
  {
    id: 1,
    title: "Movie 1",
    director: "Director 1",
    duration: 120,
    imageLink: "https://via.placeholder.com/150",
  },
  {
    id: 2,
    title: "Movie 2",
    director: "Director 2",
    duration: 90,
    budget: 1000000,
  },
  {
    id: 3,
    title: "Movie 3",
    director: "Director 3",
    duration: 150,
    description: "Description 3",
  },
];

const Main = () => {
  const [title, setTitle] = useState("");
  const [director, setDirector] = useState("");
  const [duration, setDuration] = useState(0);
  const [description, setDescription] = useState("");
  const [budget, setBudget] = useState(0);
  const [imageLink, setImageLink] = useState("");
  const [movies, setMovies] = useState(defaultMovies);

  const handleSubmit = (e: SyntheticEvent) => {
    e.preventDefault();
    console.log(
      "submit:",
      title,
      director,
      duration,
      description,
      budget,
      imageLink
    );
    const newMovie: Movie = {
      id: nextMovieId(movies),
      title: title,
      director: director,
      duration: duration,
      description: description,
      budget: budget,
      imageLink: imageLink,
    };

    setMovies([...movies, newMovie]);
  };

  const handleMovieChange = (e: SyntheticEvent) => {
    const target = e.target as HTMLInputElement;
    setTitle(target.value);
  };

  const handleDirectorChange = (e: SyntheticEvent) => {
    const target = e.target as HTMLInputElement;
    setDirector(target.value);
  };

  const handleDurationChange = (e: SyntheticEvent) => {
    const target = e.target as HTMLInputElement;
    setDuration(parseInt(target.value));
  };

  const handleDescriptionChange = (e: SyntheticEvent) => {
    const target = e.target as HTMLInputElement;
    setDescription(target.value);
  };

  const handleBudgetChange = (e: SyntheticEvent) => {
    const target = e.target as HTMLInputElement;
    setBudget(parseInt(target.value));
  };

  const handleImageLinkChange = (e: SyntheticEvent) => {
    const target = e.target as HTMLInputElement;
    setImageLink(target.value);
  };

  return (
    <main>
      <h1>Movies</h1>
      <MovieList movies={movies} />

      <div>
        <h2>Add a Movie</h2>
        <form onSubmit={handleSubmit}>
          <label htmlFor="title">Title</label>
          <input
            value={title}
            type="text"
            id="movie"
            name="movie"
            onChange={handleMovieChange}
            required
          />
          <label htmlFor="director">Director</label>
          <input
            value={director}
            type="text"
            id="director"
            name="director"
            onChange={handleDirectorChange}
            required
          />
          <label htmlFor="duration">Duration</label>
          <input
            value={duration}
            type="number"
            id="duration"
            name="duration"
            onChange={handleDurationChange}
            required
          />
          <label htmlFor="description">Description</label>
          <input
            value={description}
            type="text"
            id="description"
            name="description"
            onChange={handleDescriptionChange}
          />
          <label htmlFor="budget">Budget</label>
          <input
            value={budget}
            type="number"
            id="budget"
            name="budget"
            onChange={handleBudgetChange}
          />
          <label htmlFor="imageLink">Image Link</label>
          <input
            value={imageLink}
            type="text"
            id="imageLink"
            name="imageLink"
            onChange={handleImageLinkChange}
          />
          <button type="submit">Add Movie</button>
        </form>
      </div>
    </main>
  );
};
const nextMovieId = (movies: Movie[]) => {
  return movies.reduce((maxId, movie) => Math.max(maxId, movie.id), 0) + 1;
};

export default Main;
