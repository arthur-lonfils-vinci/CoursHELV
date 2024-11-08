import { Movie, NewMovie } from "../types";

const fetchMovies = async (): Promise<Movie[]> => {
  try {
    const response = await fetch("/api/films");
    if (!response.ok) {
      throw new Error("Failed to fetch movies : " + response.statusText);
    }
    const data = await response.json();
    if (!data || !Array.isArray(data)) {
      throw new Error("Invalid data");
    }
    return data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

const addMovie = async (movie: NewMovie): Promise<Movie> => {
  try {
    const response = await fetch("/api/films", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(movie),
    });
    if (!response.ok) {
      throw new Error("Failed to add movie : " + response.statusText);
    }
    const data = await response.json();
    return data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};

export { fetchMovies, addMovie };
