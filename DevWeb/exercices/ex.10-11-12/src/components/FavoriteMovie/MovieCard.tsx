import { useNavigate } from "react-router-dom";
import { Movie } from "../../types";
import {
  Card,
  CardMedia,
  CardActionArea,
} from "@mui/material";

interface MovieCardProps {
  movie: Movie;
}

const MovieCard = ({ movie }: MovieCardProps) => {

  const navigate = useNavigate();

  return (
    <div>
      <Card sx={{ maxWidth: 345 }}>
        <CardActionArea onClick={() => navigate(`/moviepage/${movie.id}`)}>
            <>
              <CardMedia
                component="img"
                height="500"
                image={movie.imageUrl}
                alt={movie.title}
              />
            </>
        </CardActionArea>
      </Card>
    </div>
  );
};

export default MovieCard;
