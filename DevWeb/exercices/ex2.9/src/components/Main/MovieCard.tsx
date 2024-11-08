import { useState } from "react";
import { Movie } from "../../types";
import {
  Card,
  CardContent,
  CardMedia,
  Typography,
  CardActionArea,
} from "@mui/material";

interface MovieCardProps {
  movie: Movie;
}

const MovieCard = ({ movie }: MovieCardProps) => {
  const [open, setOpen] = useState(false);
  const handleClick = () => {
    setOpen(!open);
  };

  return (
    <div>
      <Card sx={{ maxWidth: 345 }}>
        <CardActionArea onClick={handleClick}>
          {!open ? (
            <>
              <CardMedia
                component="img"
                height="500"
                image={movie.imageUrl}
                alt={movie.title}
              />
            </>
          ) : (
            <>
              <CardContent sx={{ height: 500 }}>
                <Typography gutterBottom variant="h5" component="div">
                  {movie.title}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  <strong>Réalisateur : </strong>{movie.director}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  <strong>Durée :</strong> {movie.duration} minutes
                </Typography>
                {movie.budget && (
                  <Typography variant="body2" color="text.secondary">
                    <strong>Budget :</strong> {movie.budget} millions de dollars
                  </Typography>
                )}
                {movie.description && (
                  <Typography variant="body2" color="text.secondary">
                    <strong>Description :</strong> {movie.description}
                  </Typography>
                )}
              </CardContent>
            </>
          )}
        </CardActionArea>
      </Card>
    </div>
  );
};

export default MovieCard;
