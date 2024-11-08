import { useMatch, useOutletContext } from "react-router-dom";
import { MovieContext } from "../../types";
import EditMovieForm from "../EditMovieForm";
import PageTitle from "../PageTitle";

const EditMoviePage = () => {
  const { movies, onMovieEdited }: MovieContext = useOutletContext();
  const match = useMatch("/movies/:id/edit");
  const movieId = Number(match?.params.id);
  if (isNaN(movieId)) return <p>Movie not found</p>;

  const movieFound = movies.find((movie) => movie.id === movieId);

  if (!movieFound) return <p>Movie not found</p>;
  return (
    <div>
      <PageTitle title="Edit a movie" />
      <EditMovieForm onMovieEdited={onMovieEdited} movie={movieFound}/>
      <br />
      <br />
      <br />
      <br />
    </div>
  );
};

export default EditMoviePage;
