import { Router } from "express";

import {
  readAllFilms,
  readFilmById,
  createFilm,
  deleteFilm,
  updateFilm,
  isFilmIdExist
} from "../services/films";

const router = Router();

//read all films with(out) filter
router.get("/", (req, res) => {
  if (req.query.order && typeof req.query.order !== "string") {
    return res.sendStatus(400);
  }
  return res.json(readAllFilms(req.query.order));
});

//read a single film by id
router.get("/:id", (req, res) => {
  return readFilmById(Number(req.params.id), res);
});

//create a new film
router.post("/", (req, res) => {
  return res.json(createFilm(req.body, res));
});

router.delete("/:id", (req, res) => {
  return deleteFilm(Number(req.params.id), res);
});

router.patch("/:id", (req, res) => {
  return updateFilm(Number(req.params), req.body, res);
});

router.put("/:id", (req, res) => {
  const id = Number(req.params.id);
  const film = req.body
  
  if(isFilmIdExist(id)){
    return updateFilm(id, film, res);
  } else {
    return createFilm(film, res);
  }
});

export default router;
