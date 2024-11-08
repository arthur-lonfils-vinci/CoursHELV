import { SyntheticEvent, useState } from "react";
import { Movie } from "../types";
import "./MovieForm.css";

interface EditMovieFormProps {
  movie: Movie;
  onMovieEdited: (movie: Movie) => void;
}

const EditMovieForm = ({ movie, onMovieEdited }: EditMovieFormProps) => {
  const [title, setTitle] = useState(movie.title);
  const [director, setDirector] = useState(movie.director);
  const [duration, setDuration] = useState<number | undefined>(
    movie.duration ? movie.duration : undefined
  );
  const [imageUrl, setImageUrl] = useState<string | undefined>(movie.imageUrl);
  const [description, setDescription] = useState<string | undefined>(
    movie.description
  );
  const [budget, setBudget] = useState<number | undefined>(
    movie.budget ? movie.budget : undefined
  );

  const handleSubmit = (e: SyntheticEvent) => {
    e.preventDefault();
    console.log("Edit movie, budget:", budget);
    onMovieEdited({
      id: movie.id,
      title,
      director,
      duration,
      imageUrl : imageUrl === "" ? undefined : imageUrl,
      description : description === "" ? undefined : description,
      budget : budget !== undefined && isNaN(budget) ? undefined : budget,
    } as Movie);
  };
  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Titre :</label>
        <input
          type="text"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Réalisateur :</label>
        <input
          type="text"
          value={director}
          onChange={(e) => setDirector(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Durée :</label>
        <input
          type="number"
          value={duration ?? ""}
          onChange={(e) => setDuration(parseInt(e.target.value))}
          required
        />
      </div>
      <div>
        <label>URL de l'image :</label>
        <input
          type="text"
          value={imageUrl}
          onChange={(e) => setImageUrl(e.target.value)}
        />
      </div>
      <div>
        <label>Description :</label>
        <textarea
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
      </div>
      <div>
        <label>Budget :</label>
        <input
          type="number"
          value={budget ?? ""}
          onChange={(e) => setBudget(parseInt(e.target.value))}
        />
      </div>
      <button type="submit">Éditer</button>
    </form>
  );
};

export default EditMovieForm;
