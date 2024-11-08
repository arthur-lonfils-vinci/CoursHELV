import { SyntheticEvent, useState } from "react";
import { Movie } from "../types";
import { Box, Button, Container, TextField } from "@mui/material";

interface AddMovieFormProps {
  onMovieAdded: (movie: Movie) => void;
}

const AddMovieForm = ({ onMovieAdded }: AddMovieFormProps) => {
  const [title, setTitle] = useState("");
  const [director, setDirector] = useState("");
  const [duration, setDuration] = useState(0);
  const [imageUrl, setImageUrl] = useState("");
  const [description, setDescription] = useState("");
  const [budget, setBudget] = useState(0);

  const handleSubmit = (e: SyntheticEvent) => {
    e.preventDefault();
    onMovieAdded({ title, director, duration, imageUrl, description, budget });
    setTitle("");
    setDirector("");
    setDuration(0);
    setImageUrl("");
    setDescription("");
    setBudget(0);
  };
  return (
    <Container sx={{ paddingTop: "2rem", maxWidth: "600px" }}>
      <form onSubmit={handleSubmit}>
        <Box sx={{ marginBottom: 2 }}>
          <TextField
            label="Titre :"
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
            fullWidth
          />
        </Box>
        <Box sx={{ marginBottom: 2 }}>
          <TextField
            label="Réalisateur :"
            type="text"
            value={director}
            onChange={(e) => setDirector(e.target.value)}
            required
            fullWidth
          />
        </Box>
        <Box sx={{ marginBottom: 2 }}>
          <TextField
            label="Durée :"
            type="number"
            value={duration ? duration : ""}
            onChange={(e) => setDuration(parseInt(e.target.value))}
            required
            fullWidth
          />
        </Box>
        <Box sx={{ marginBottom: 2 }}>
          <TextField
            label="URL de l'image :"
            type="text"
            value={imageUrl}
            onChange={(e) => setImageUrl(e.target.value)}
            fullWidth
          />
        </Box>
        <Box sx={{ marginBottom: 2 }}>
          <TextField
            label="Description :"
            type="text"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            fullWidth
          />
        </Box>
        <Box sx={{ marginBottom: 2 }}>
          <TextField
            label="Budget :"
            type="number"
            value={budget ? budget : ""}
            onChange={(e) => setBudget(parseInt(e.target.value))}
            fullWidth
          />
        </Box>
        <Button type="submit" variant="contained" color="primary">
          Ajouter
        </Button>
      </form>
    </Container>
  );
};

export default AddMovieForm;
