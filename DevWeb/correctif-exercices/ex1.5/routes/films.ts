import { Router } from "express";

import { Film, NewFilm } from "../types";

const router = Router();

const films: Film[] = [
  {
    id: 1,
    title: "Shang-Chi and the Legend of the Ten Rings",
    director: "Destin Daniel Cretton",
    duration: 132,
    imageUrl:
      "https://upload.wikimedia.org/wikipedia/en/7/74/Shang-Chi_and_the_Legend_of_the_Ten_Rings_poster.jpeg",
    description:
      "Shang-Chi, the master of unarmed weaponry-based Kung Fu, is forced to confront his past after being drawn into the Ten Rings organization.",
    budget: 150,
  },
  {
    id: 2,
    title: "The Matrix",
    director: "Lana Wachowski, Lilly Wachowski",
    duration: 136,
    imageUrl:
      "https://upload.wikimedia.org/wikipedia/en/c/c1/The_Matrix_Poster.jpg",
    description:
      "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
    budget: 63,
  },
  {
    id: 3,
    title: "Summer Wars",
    director: "Mamoru Hosoda",
    duration: 114,
    imageUrl:
      "https://upload.wikimedia.org/wikipedia/en/7/7d/Summer_Wars_poster.jpg",
    description:
      "A young math genius solves a complex equation and inadvertently puts a virtual world's artificial intelligence in a position to destroy Earth.",
    budget: 18.7,
  },
  {
    id: 4,
    title: "The Meyerowitz Stories",
    director: "Noah Baumbach",
    duration: 112,
    imageUrl:
      "https://upload.wikimedia.org/wikipedia/en/a/af/The_Meyerowitz_Stories.png",
    description:
      "An estranged family gathers together in New York City for an event celebrating the artistic work of their father.",
  },
  {
    id: 5,
    title: "her",
    director: "Spike Jonze",
    duration: 126,
    imageUrl:
      "https://upload.wikimedia.org/wikipedia/en/4/44/Her2013Poster.jpg",
    description:
      "In a near future, a lonely writer develops an unlikely relationship with an operating system designed to meet his every need.",
    budget: 23,
  },
];

// Read all films, filtered by minimum-duration if the query param exists
router.get("/", (req, res) => {
  if (req.query["minimum-duration"] === undefined) {
    return res.send(films);
  }

  const minDuration = Number(req.query["minimum-duration"]);

  if (isNaN(minDuration) || minDuration <= 0) {
    return res.sendStatus(400);
  }

  const filteredFilms = films.filter((film) => film.duration >= minDuration);

  return res.send(filteredFilms);
});

// Read a film by id
router.get("/:id", (req, res) => {
  const id = Number(req.params.id);

  if (isNaN(id)) {
    return res.sendStatus(400);
  }

  const film = films.find((film) => film.id === id);

  if (film === undefined) {
    return res.sendStatus(404);
  }

  return res.send(film);
});

// Create a new film
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
    ("budget" in body &&
      (typeof body.budget !== "number" || body.budget <= 0)) ||
    ("description" in body &&
      (typeof body.description !== "string" || !body.description.trim())) ||
    ("imageUrl" in body &&
      (typeof body.imageUrl !== "string" || !body.imageUrl.trim()))
  ) {
    return res.sendStatus(400);
  }

  // Challenge of ex1.4 : To be complete, we should check that the keys of the body object are only the ones we expect
  const expectedKeys = [
    "title",
    "director",
    "duration",
    "budget",
    "description",
    "imageUrl",
  ];
  const bodyKeys = Object.keys(body);
  const extraKeys = bodyKeys.filter((key) => !expectedKeys.includes(key));
  if (extraKeys.length > 0) {
    return res.sendStatus(400);
  }
  // End of challenge

  const newFilm = body as NewFilm;

  const existingFilm = films.find(
    (film) =>
      film.title.toLowerCase() === newFilm.title.toLowerCase() &&
      film.director.toLowerCase() === newFilm.director.toLowerCase()
  );

  if (existingFilm) {
    return res.sendStatus(409);
  }

  const nextId =
    films.reduce((acc, film) => (film.id > acc ? film.id : acc), 0) + 1;

  const addedFilm: Film = { id: nextId, ...newFilm };

  films.push(addedFilm);

  return res.json(addedFilm);
});

export default router;
