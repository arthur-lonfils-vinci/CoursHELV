import { Router } from "express";
import { film } from "../types";

const films: film[] = [
    {
        id: 1,
        title: "The Shawshank Redemption",
        director: "Frank Darabont",
        duration: 142,
        budget: 25000000,
        description: new URL("https://en.wikipedia.org/wiki/The_Shawshank_Redemption"),
        imageURL: new URL("https://en.wikipedia.org/wiki/The_Shawshank_Redemption#/media/File:ShawshankRedemptionMoviePoster.jpg"),
    },

    {
        id: 2,
        title: "The Godfather",
        director: "Francis Ford Coppola",
        duration: 175,
        budget: 6000000,
        description: new URL("https://en.wikipedia.org/wiki/The_Godfather"),
        imageURL: new URL("https://en.wikipedia.org/wiki/The_Godfather#/media/File:Godfather_ver1.jpg"),
    },

    {
        id: 3,
        title: "The Dark Knight",
        director: "Christopher Nolan",
        duration: 152,
        budget: 185000000,
        description: new URL("https://en.wikipedia.org/wiki/The_Dark_Knight_(film)"),
        imageURL: new URL("https://en.wikipedia.org/wiki/The_Dark_Knight_(film)#/media/File:Dark_Knight.jpg"),
    },
]

const router = Router();

router.get("/", (_req, res) => {
    res.json(films);
});

export default router;