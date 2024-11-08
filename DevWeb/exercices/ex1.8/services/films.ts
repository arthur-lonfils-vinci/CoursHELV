import path from "node:path";
import { Film, NewFilm } from "../types";
import { parse, serialize } from "../utils/json";
import { Response } from "express";
const jsonDbPath = path.join(__dirname, "/../data/films.json");

const films = parse(jsonDbPath) as Film[];

//
//Function for the services
//

function readAllFilms(order: string | undefined): Film[] {
  const orderByTitle = order && order.includes("title") ? order : undefined;
  const orderByDuration =
    order && order.includes("duration") ? order : undefined;
  const orderByDirector =
    order && order.includes("director") ? order : undefined;

  let orderedFilms: Film[] = [];
  if (orderByTitle)
    orderedFilms = [...films].sort((a, b) => a.title.localeCompare(b.title));

  if (orderByTitle === "-title") orderedFilms = orderedFilms.reverse();

  if (orderByDuration)
    orderedFilms = [...films].sort((a, b) => a.duration - b.duration);

  if (orderByDuration === "-duration") orderedFilms = orderedFilms.reverse();

  if (orderByDirector)
    orderedFilms = [...films].sort((a, b) =>
      a.director.localeCompare(b.director)
    );

  if (orderByDirector === "-director") orderedFilms = orderedFilms.reverse();

  return orderedFilms.length === 0 ? films : orderedFilms;
}

function readFilmById(id: number, response: Response) {
  if (!isFilmIdExist(id)) {
    return response
      .status(409)
      .json({ message: "Error 404 : Film not found" });
  }
  return response.json(films.find((film) => film.id === id));
}

function createFilm(body: unknown, response: Response) {
  if (!isFilmDataValidForCreation(body)) {
    return response
      .status(400)
      .json({ message: "Error 400 : Invalid film data" });
  }

  if (isFilmExists(body as Film)) {
    return response
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
  return addedFilm;
}

function deleteFilm(id: number, response: Response) {
  const filmIndex = films.findIndex((film) => film.id === id);

  if (filmIndex === -1) {
    return response.status(404).json({ message: "Error 404 : Film not found" });
  }

  films.splice(filmIndex, 1);
  serialize(jsonDbPath, films);
  return response.json({ message: "Film deleted successfully" });
}

function updateFilm(id: number, body: unknown, response: Response) {
  if (!isFilmIdExist(id)) {
    return response.status(404).json({ message: "Error 404 : Film not found" });
  }

  if (!isFilmDataValidForUpdate(body)) {
    return response
      .status(400)
      .json({ message: "Error 400 : Invalid film data" });
  }

  const film = films.find((film) => film.id === id);
  const update = body as Film;

  const updatedFilm = { ...film, ...update };
  const filmIndex = films.findIndex((film) => film.id === id);
  films[filmIndex] = updatedFilm;
  serialize(jsonDbPath, films);
  return response.json({ message: "Film updated successfully" });
}

//
///Function to help for the services functions
//

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

function isFilmDataValidForCreation(body: unknown) {
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
    return false;
  }
  return true;
}

function isFilmDataValidForUpdate(body: unknown) {
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
    return false;
  }
  return true;
}

function isFilmIdExist(id: Number) {
  const film = films.find((film) => film.id === id);

  if (!film) {
    return false;
  }

  return true;
}

export {
  readAllFilms,
  readFilmById,
  createFilm,
  deleteFilm,
  updateFilm,
  isFilmDataValidForCreation,
  isFilmDataValidForUpdate,
  isFilmIdExist,
  isFilmExists,
};
