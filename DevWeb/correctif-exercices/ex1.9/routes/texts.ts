import { Router } from "express";

import { NewText } from "../types";

import { containsOnlyExpectedKeys } from "../utils/validate";

import {
  createOne,
  deleteOne,
  readAll,
  readOne,
  updateOne,
} from "../services/texts";

const router = Router();

const expectedKeys = ["content", "level"];

const expectedLevels = ["easy", "medium", "hard"];

// Read all texts, filtered by level if the query param exists
router.get("/", (req, res) => {
  const level =
    "level" in req.query && typeof req.query["level"] === "string"
      ? req.query["level"]
      : undefined;

  if (level !== undefined && !expectedLevels.includes(level)) {
    return res.sendStatus(400);
  }

  const filteredTexts = readAll(level);

  return res.send(filteredTexts);
});

// Read a text by id
router.get("/:id", (req, res) => {
  if (typeof req.params.id !== "string") {
    return res.sendStatus(400);
  }

  const text = readOne(req.params.id);

  if (text === undefined) {
    return res.sendStatus(404);
  }

  return res.send(text);
});

// Create a new text
router.post("/", (req, res) => {
  const body: unknown = req.body;

  if (
    !body ||
    typeof body !== "object" ||
    !("content" in body) ||
    !("level" in body) ||
    typeof body.content !== "string" ||
    typeof body.level !== "string" ||
    !body.content.trim() ||
    !expectedLevels.includes(body.level)
  ) {
    return res.sendStatus(400);
  }

  if (!containsOnlyExpectedKeys(body, expectedKeys)) {
    return res.sendStatus(400);
  }

  const newText = body as NewText;

  const createdText = createOne(newText);

  if (!createdText) {
    return res.sendStatus(409);
  }

  return res.json(createdText);
});

// Delete a text by id
router.delete("/:id", (req, res) => {
  const id = req.params.id;

  if (typeof id !== "string") {
    return res.sendStatus(400);
  }

  const deletedText = deleteOne(id);

  if (!deletedText) {
    return res.sendStatus(404);
  }

  return res.send(deletedText);
});

// Update a text only if all properties are given
router.put("/:id", (req, res) => {
  const body: unknown = req.body;

  if (
    !body ||
    typeof body !== "object" ||
    !("content" in body) ||
    !("level" in body) ||
    typeof body.content !== "string" ||
    typeof body.level !== "string" ||
    !body.content.trim() ||
    !expectedLevels.includes(body.level)
  ) {
    return res.sendStatus(400);
  }

  if (!containsOnlyExpectedKeys(body, expectedKeys)) {
    return res.sendStatus(400);
  }

  const id = req.params.id;

  if (typeof id !== "string") {
    return res.sendStatus(400);
  }

  const updatedText = updateOne(id, body as NewText);

  if (!updatedText) {
    return res.sendStatus(404);
  }

  return res.send(updatedText);
});

export default router;
