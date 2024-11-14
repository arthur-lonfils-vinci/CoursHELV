import { SyntheticEvent, useState } from "react";
import { Movie } from "../../types";
import {
  Box,
  Button,
  TextField,
  Card,
  CardContent,
  CardMedia,
  CardActionArea,
} from "@mui/material";

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

  const [open, setOpen] = useState(false);
  const handleClick = () => {
    setOpen(!open);
  };

  //const theme = useTheme();

  return (
    <div>
      <Card sx={{ maxWidth: 345 }}>
        {!open ? (
          <>
            <CardActionArea onClick={handleClick}>
              <CardMedia
                component="img"
                height="500"
                image="https://www.pngall.com/wp-content/uploads/10/Plus-Symbol-Vector-PNG-Cutout.png"
                alt="Ajouter un Film"
              />
            </CardActionArea>
          </>
        ) : (
          <>
            {" "}
            <Box
              component="form"
              onSubmit={handleSubmit}
              sx={{
                display: "flex",
                flexDirection: "column",
                gap: 2,
              }}
            >
              <CardContent
                sx={{
                  height: 500,
                  display: "flex",
                  flexDirection: "column",
                  gap: 1,
                }}
              >
                <TextField
                  label="Titre"
                  value={title}
                  onChange={(e) => setTitle(e.target.value)}
                  required
                />
                <TextField
                  label="Réalisateur"
                  value={director}
                  onChange={(e) => setDirector(e.target.value)}
                  required
                />
                <TextField
                  label="Durée"
                  type="number"
                  value={duration}
                  onChange={(e) => setDuration(parseInt(e.target.value))}
                  required
                />
                <TextField
                  label="URL de l'image"
                  value={imageUrl}
                  onChange={(e) => setImageUrl(e.target.value)}
                />
                <TextField
                  label="Description"
                  value={description}
                  onChange={(e) => setDescription(e.target.value)}
                />
                <TextField
                  label="Budget"
                  type="number"
                  value={budget}
                  onChange={(e) => setBudget(parseInt(e.target.value))}
                />
                <Box sx={{ display: "flex", flexDirection: "row", gap: 2 }}>
                  <Button type="submit" variant="contained">
                    Ajouter
                  </Button>
                  <Button onClick={handleClick} variant="contained">
                    Annuler
                  </Button>
                </Box>
              </CardContent>
            </Box>
          </>
        )}
      </Card>
    </div>
  
  );
};

export default AddMovieForm;
