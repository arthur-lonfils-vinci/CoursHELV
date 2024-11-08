import { useOutletContext } from "react-router-dom";
import { MovieContext } from "../../types";
import AddMovieForm from "../AddMovieForm";
import PageTitle from "../PageTitle";

const AddMoviePage = () => {
  const { onMovieAdded }: MovieContext = useOutletContext();
  return (
    <div>
      <PageTitle title="Add a movie" />
      <AddMovieForm onMovieAdded={onMovieAdded} />
      <br />
      <br />
      <br />
      <br />
    </div>
  );
};

export default AddMoviePage;
