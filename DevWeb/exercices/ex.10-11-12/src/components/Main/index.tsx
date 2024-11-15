import { Box } from "@mui/material";
import { Movie } from "../../types";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

interface HomePageProps {
    defaultMovies: Movie[];
}

function HomePage({ defaultMovies }: HomePageProps) {

    const navigate = useNavigate();

    const [movies] = useState<Movie[]>(defaultMovies);

    return (
        <Box flex={"flex"} justifyContent={"center"} width={"70%"}>
            <Box flex={"flex"} p={"5%"}>
                <h1>Welcome to our movie website!</h1>
                <p>Here you can explore a variety of movies across different genres. <br />
                    Browse through our collection and find your next favorite movie to watch.</p>
            </Box>

            <Box flex={"flex"} p={"5%"}>
                <h2>Liste de Films :</h2>

                {movies.map((movie) => (
                    <Box key={movie.id} flex={"flex"} justifyContent={"center"} pb={"2%"} pl={"2%"}>
                        <div>
                            <h2 onClick={() => navigate(`/moviepage/${movie.id}`)}>{movie.title}</h2>
                        </div>
                    </Box>
                ))}
            </Box>
        </Box>
    );
}

export default HomePage;
