// ...existing code...
import { Router } from "express";
import {
  readAllBooks,
  readBookById,
} from "../services/books";
import { authorize, isAdmin } from "../utils/auths";

const router = Router();

router.get("/error", (_req, _res, _next) => {
  throw new Error("This is an error");
});

router.get("/", authorize, isAdmin, (req, res) => {
  if (req.query.order && typeof req.query.order !== "string") {
    return res.sendStatus(400);
  }

  const books = readAllBooks(req.query.order);
  return res.json(books);
});


router.get("/:id", authorize, isAdmin, (req, res) => {
  const id = Number(req.params.id);
  const book = readBookById(id);
  if (!book) return res.sendStatus(404);
  return res.json(book);
});

export default router;