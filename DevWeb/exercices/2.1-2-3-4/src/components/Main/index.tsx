import Cinema from "./Cinema/cinema";
import Movie from "../Main/Cinema/movie";
import PageTitle from "./PageTitle/pagetitle";

const Main = () => {
    const pageTitle = "Informations sur les films dans les cinémas";

  const cinema1Name = "UGC DeBrouckère";

  const moviesCinema1 = [
    {
      title: "HAIKYU-THE DUMPSTER BATTLE",
      director: "Susumu Mitsunaka",
    },
    {
      title: "GOODBYE JULIA",
      director: "Mohamed Kordofani",
    },
    {
      title: "INCEPTION",
      director: "Christopher Nolan",
    },
    {
      title: "PARASITE",
      director: "Bong Joon-ho",
    },
  ];

  const cinema2Name = "UGC Toison d'Or";

  const moviesCinema2 = [
    {
      title: "THE WATCHERS",
      director: "Ishana Night Shyamalan",
    },
    {
      title: "BAD BOYS: RIDE OR DIE",
      director: "Adil El Arbi, Bilall Fallah",
    },
    {
      title: "TENET",
      director: "Christopher Nolan",
    },
    {
      title: "THE IRISHMAN",
      director: "Martin Scorsese",
    },
  ];
    return (
    <main>
      
      <PageTitle title={pageTitle} />

      <Cinema name={cinema1Name}>
        {moviesCinema1.map((movie) => (
          <Movie key={movie.title} title={movie.title} director={movie.director} />
        ))}
      </Cinema>

      <Cinema name={cinema2Name}>
        {moviesCinema2.map((movie) => (
          <Movie key={movie.title} title={movie.title} director={movie.director} />
        ))}
      </Cinema>
    </main>
    );
};

export default Main;