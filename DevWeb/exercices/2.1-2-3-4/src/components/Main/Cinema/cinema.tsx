import { ReactElement } from "react";
import Movie from "./movie.tsx";

interface CinemaProps {
  name: string;
  children: ReactElement<typeof Movie>[] | ReactElement<typeof Movie>;
}

const Cinema = (props: CinemaProps) => {
  return (
    <div key={props.name}>
      <h2>{props.name}</h2>
      <ul>{props.children}</ul>
    </div>
  );
};

export default Cinema;
