import {
  Box,
  Card,
  CardContent,
  CardHeader,
  CardMedia,
  Typography,
} from "@mui/material";
import { Movie } from "../types";

interface MovieCardProps {
  movie: Movie;
}

const MovieCard = ({ movie }: MovieCardProps) => {
  return (
    <Card variant="outlined" sx={{ width: "18rem" }}>
      <CardHeader title={movie.title}></CardHeader>
      {movie.imageUrl && (
        <CardMedia
          component="img"
          sx={{ width: 150, marginLeft: "1rem" }}
          image={movie.imageUrl}
          alt={movie.title}
        />
      )}
      <CardContent>
        <Box>
          <Typography variant="h6" display="inline">
            Réalisateur :
          </Typography>
          <Typography
            display="inline"
            sx={{
              fontWeight: "light",
              fontStyle: "italic",
              color: "text.secondary",
            }}
          >
            {` ${movie.director}`}
          </Typography>
        </Box>

        <Box>
          <Typography variant="h6" display="inline">
            Durée :
          </Typography>
          <Typography
            display="inline"
            sx={{
              fontWeight: "light",
              fontStyle: "italic",
              color: "text.secondary",
            }}
          >
            {` ${movie.duration} minutes`}
          </Typography>
        </Box>
        {movie.budget && (
          <Box>
            <Typography variant="h6" display="inline">
              Budget :
            </Typography>
            <Typography
              display="inline"
              sx={{
                fontWeight: "light",
                fontStyle: "italic",
                color: "text.secondary",
              }}
            >
              {` ${movie.budget} millions de dollars`}
            </Typography>
          </Box>
        )}
        {movie.description && (
          <Box>
            <Typography variant="h6" display="inline">
              Description :
            </Typography>
            <Typography
              display="inline"
              sx={{
                fontWeight: "light",
                fontStyle: "italic",
                color: "text.secondary",
              }}
            >
              {` ${movie.description}`}
            </Typography>
          </Box>
        )}
      </CardContent>
    </Card>
  );
};

export default MovieCard;
