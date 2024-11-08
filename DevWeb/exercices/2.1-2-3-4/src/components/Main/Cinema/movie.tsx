interface MovieProps {
  title: string;
  director: string;
}

const Movie = (props: MovieProps) => {
  return (
    <li key={props.title}>
      {props.title} (Directed by {props.director})
    </li>
  );
};

export default Movie;
