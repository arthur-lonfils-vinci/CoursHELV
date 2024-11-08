import { useOutletContext } from "react-router-dom";
import MovieTitleList from "../MovieTitleList";
import PageTitle from "../PageTitle";
import { MovieContext } from "../../types";

const HomePage = () => {
  const { movies }: MovieContext = useOutletContext();
  return (
    <div>
      <PageTitle title="myMovies" />
      <p>
        Welcome to myMovies, a site where you can find info about cinemas,
        movies...
      </p>
      <h4>My favorites movies </h4>
      <MovieTitleList movies={movies} />
    </div>
  );
};
export default HomePage;
