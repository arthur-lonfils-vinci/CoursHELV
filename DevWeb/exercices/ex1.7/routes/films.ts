import { Router } from "express";
import path from "node:path";
import { Film, NewFilm } from "../types";
import { parse, serialize } from "../utils/json";
const jsonDbPath = path.join(__dirname, "/../data/films.json");

const router = Router();

const films = parse(jsonDbPath) as Film[];

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
    body.duration <= 0 ||
    Object.keys(body).some(
      (key) =>
        ![
          "title",
          "director",
          "duration",
          "budget",
          "description",
          "imageURL",
        ].includes(key)
    )
  ) {
    return res.status(400).json({ message: "Error 400 : Invalid film data" });
  }

  if (isFilmExists(body as Film)) {
    return res.status(409).json({ message: "Error 409 : Film already exists" });
  }

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
  serialize(jsonDbPath, films);
  return res.json(addedFilm);
});

router.delete("/:id", (req, res) => {
  const id = Number(req.params.id);
  const filmIndex = films.findIndex((film) => film.id === id);

  if (filmIndex === -1) {
    return res.status(404).json({ message: "Error 404 : Film not found" });
  }

  films.splice(filmIndex, 1);

  return res.json({ message: "Film deleted successfully" });
});

router.patch("/:id", (req, res) => {
  const id = Number(req.params.id);
  const film = films.find((film) => film.id === id);

  if (!film) {
    return res.status(404).json({ message: "Error 404 : Film not found" });
  }

  const body: unknown = req.body;

  if (
    !body ||
    typeof body !== "object" ||
    ("title" in body &&
      (typeof body.title !== "string" || !body.title.trim())) ||
    ("director" in body &&
      (typeof body.director !== "string" || !body.director.trim())) ||
    ("duration" in body &&
      (typeof body.duration !== "number" || body.duration <= 0)) ||
    ("budget" in body &&
      (typeof body.budget !== "number" || body.budget <= 0)) ||
    ("description" in body &&
      (typeof body.description !== "string" || !body.description.trim())) ||
    ("imageURL" in body &&
      (typeof body.imageURL !== "string" || !body.imageURL.trim())) ||
    Object.keys(body).some(
      (key) =>
        ![
          "title",
          "director",
          "duration",
          "budget",
          "description",
          "imageURL",
        ].includes(key)
    )
  ) {
    return res
      .status(400)
      .json({ message: "Error 400 : Invalid film data\nContent : " });
  }

  const { title, director, duration, budget, description, imageURL } =
    body as Partial<NewFilm>;

  if (title) {
    film.title = title;
  }
  if (director) {
    film.director = director;
  }
  if (duration) {
    film.duration = duration;
  }
  if (budget) {
    film.budget = budget;
  }
  if (description) {
    film.description = description;
  }
  if (imageURL) {
    film.imageURL = imageURL;
  }
  serialize(jsonDbPath, films);
  return res.status(200).json("Film updated successfully");
});

router.put("/:id", (req, res) => {
  const id = Number(req.params.id);
  const film = films.find((film) => film.id === id);

  if (!film) {
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
      body.duration <= 0 || 
      Object.keys(body).some(
        (key) =>
          ![
            "title",
            "director",
            "duration",
            "budget",
            "description",
            "imageURL",
          ].includes(key)
      )
    ) {
      return res.status(400).json({ message: "Error 400 : Invalid film data" });
    }

    if (isFilmExists(body as Film)) {
      return res
        .status(409)
        .json({ message: "Error 409 : Film already exists" });
    }

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
    serialize(jsonDbPath, films);

    return res.status(201).json({ message: "Film created successfully" });
  } else {
    const id = Number(req.params.id);
    const film = films.find((film) => film.id === id);

    if (!film) {
      return res.status(404).json({ message: "Error 404 : Film not found" });
    }

    const body: unknown = req.body;

    if (
      !body ||
      typeof body !== "object" ||
      ("title" in body &&
        (typeof body.title !== "string" || !body.title.trim())) ||
      ("director" in body &&
        (typeof body.director !== "string" || !body.director.trim())) ||
      ("duration" in body &&
        (typeof body.duration !== "number" || body.duration <= 0)) ||
      ("budget" in body &&
        (typeof body.budget !== "number" || body.budget <= 0)) ||
      ("description" in body &&
        (typeof body.description !== "string" || !body.description.trim())) ||
      ("imageURL" in body &&
        (typeof body.imageURL !== "string" || !body.imageURL.trim())) ||
      Object.keys(body).some(
        (key) =>
          ![
            "title",
            "director",
            "duration",
            "budget",
            "description",
            "imageURL",
          ].includes(key)
      )
    ) {
      return res.status(400).json({ message: "Error 400 : Invalid film data" });
    }

    const { title, director, duration, budget, description, imageURL } =
      body as Partial<NewFilm>;

    if (title) {
      film.title = title;
    }
    if (director) {
      film.director = director;
    }
    if (duration) {
      film.duration = duration;
    }
    if (budget) {
      film.budget = budget;
    }
    if (description) {
      film.description = description;
    }
    if (imageURL) {
      film.imageURL = imageURL;
    }
    serialize(jsonDbPath, films);
    return res.status(200).json("Film updated successfully");
  }
});

export default router;

function isFilmExists(body: Film) {
  const bodyTitle = body.title.toLowerCase();
  const bodyDirector = body.director.toLowerCase();

  const filmExists = films.some(
    (film) =>
      film.title.toLowerCase() === bodyTitle &&
      film.director.toLowerCase() === bodyDirector
  );

  if (filmExists) {
    return true;
  }
  return false;
}
