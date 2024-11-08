import { Router } from "express";

import { Film, NewFilm } from "../types";

const router = Router();

const films: Film[] = [
  {
    id: 1,
    title: "The Shawshank Redemption",
    director: "Frank Darabont",
    duration: 142,
    budget: 25000000,
    description: new URL(
      "https://en.wikipedia.org/wiki/The_Shawshank_Redemption"
    ),
    imageURL: new URL(
      "https://en.wikipedia.org/wiki/The_Shawshank_Redemption#/media/File:ShawshankRedemptionMoviePoster.jpg"
    ),
  },

  {
    id: 2,
    title: "The Godfather",
    director: "Francis Ford Coppola",
    duration: 175,
    budget: 6000000,
    description: new URL("https://en.wikipedia.org/wiki/The_Godfather"),
    imageURL: new URL(
      "https://en.wikipedia.org/wiki/The_Godfather#/media/File:Godfather_ver1.jpg"
    ),
  },

  {
    id: 3,
    title: "The Dark Knight",
    director: "Christopher Nolan",
    duration: 152,
    budget: 185000000,
    description: new URL(
      "https://en.wikipedia.org/wiki/The_Dark_Knight_(film)"
    ),
    imageURL: new URL(
      "https://en.wikipedia.org/wiki/The_Dark_Knight_(film)#/media/File:Dark_Knight.jpg"
    ),
  },
  {
    id: 4,
    title: "Pulp Fiction",
    director: "Quentin Tarantino",
    duration: 154,
    budget: 8000000,
    description: new URL("https://en.wikipedia.org/wiki/Pulp_Fiction"),
    imageURL: new URL(
      "https://en.wikipedia.org/wiki/Pulp_Fiction#/media/File:Pulp_Fiction_(1994)_poster.jpg"
    ),
  },
  {
    id: 5,
    title: "Inception",
    director: "Christopher Nolan",
    duration: 148,
    budget: 160000000,
    description: new URL("https://en.wikipedia.org/wiki/Inception"),
    imageURL: new URL(
      "https://en.wikipedia.org/wiki/Inception#/media/File:Inception_(2010)_theatrical_poster.jpg"
    ),
  },
  {
    id: 6,
    title: "Fight Club",
    director: "David Fincher",
    duration: 139,
    budget: 63000000,
    description: new URL("https://en.wikipedia.org/wiki/Fight_Club"),
    imageURL: new URL(
      "https://en.wikipedia.org/wiki/Fight_Club#/media/File:Fight_Club_poster.jpg"
    ),
  },
  {
    id: 7,
    title: "Forrest Gump",
    director: "Robert Zemeckis",
    duration: 142,
    budget: 55000000,
    description: new URL("https://en.wikipedia.org/wiki/Forrest_Gump"),
    imageURL: new URL(
      "https://en.wikipedia.org/wiki/Forrest_Gump#/media/File:Forrest_Gump_poster.jpg"
    ),
  },
  {
    id: 8,
    title: "The Matrix",
    director: "Lana Wachowski, Lilly Wachowski",
    duration: 136,
    budget: 63000000,
    description: new URL("https://en.wikipedia.org/wiki/The_Matrix"),
    imageURL: new URL(
      "https://en.wikipedia.org/wiki/The_Matrix#/media/File:The_Matrix_Poster.jpg"
    ),
  },
];

//read all films and/or filter by minimum duration
router.get("/", (_req, res) => {
  if (_req.query["by-minimum-duration"]) {
    const minDuration = Number(_req.query["by-minimum-duration"]);
    const filteredFilms = films.filter((films) => {
      return films.duration >= minDuration;
    });

    return res.json(filteredFilms);
  }

  if (_req.query["by-firstLetter"]) {
    const firstLetter = _req.query["by-firstLetter"] as string;
    const filteredFilms = films.filter((films) => {
      return films.title.charAt(0).toLowerCase() === firstLetter.toLowerCase();
    });
    return res.json(filteredFilms);
  }

  if (_req.query["by-director"]) {
    const director = _req.query["by-director"] as string;
    const filteredFilms = films.filter((films) => {
      return films.director.toLowerCase().includes(director.toLowerCase());
    });
    return res.json(filteredFilms);
  }

  return res.json(films);
});

//read a single film by id
router.get("/:id", (_req, res) => {
  const id = Number(_req.params.id);
  const film = films.find((film) => film.id === id);
  if (!film) {
    return res.status(404).json({ message: "Error 404 : Film not found" });
  }
  return res.json(film);
});

//create a new film
router.post("/", (req, res) => {
  const body: unknown = req.body;
  if (
    !body ||
    typeof body !== "object" ||
    !("title" in body) ||
    !("director" in body) ||
    !("duration" in body) ||
    typeof body.title !== "string" ||
    typeof body.director !== "string" ||
    typeof body.duration !== "number" ||
    !body.title.trim() ||
    !body.director.trim() ||
    body.duration <= 0
  ) {
    return res.status(400).json({message : "Error 400 : Invalid film data"});
  }

  const bodyTitle = body.title.toLowerCase();
  const bodyDirector = body.director.toLowerCase();

  const filmExists = films.some(
    (film) =>
      film.title.toLowerCase() === bodyTitle &&
      film.director.toLowerCase() === bodyDirector
  );

  if (filmExists) {
    return res.status(409).json({ message: "Error 409 : Film already exists" });
  }

  // Proceed with the next steps if no match is found
  const { title, director, duration, budget, description, imageURL } =
    body as NewFilm;

  const nextId =
    films.reduce((maxId, film) => (film.id > maxId ? film.id : maxId), 0) + 1;

  const addedFilm: Film = {
    id: nextId,
    title,
    director,
    duration,
    budget,
    description,
    imageURL,
  };

  films.push(addedFilm);

  return res.json(addedFilm);
});

export default router;
