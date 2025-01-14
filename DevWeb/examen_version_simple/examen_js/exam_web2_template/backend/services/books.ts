// Create the pizzas service based on the drinks.ts service
import path from "node:path";
import { Book, NewBook } from "../types";
import { parse, serialize } from "../utils/json";
const jsonDbPath = path.join(__dirname, "/../data/pizzas.json");

const defaultBooks: Book[] = [
  {
    id: 1,
    title: "L'étoile Jaune",
    author: "Joseph Juif",
    creationDate: "2021-10-01",
    image: "https://www.google.com",
  },
  {
    id: 2,
    title: "Le Grand Voyage",
    author: "Charlotte Dupont",
    creationDate: "2021-11-15",
  },
  {
    id: 3,
    title: "La Nuit de l'Océan",
    author: "Marcel Marin",
    creationDate: "2022-01-10",
    image: "https://example.com/image2.jpg",
  },
  {
    id: 4,
    title: "Terre Promise",
    author: "Anne Martin",
    creationDate: "2023-03-05",
  },
];


function readAllBooks(order: string | undefined): Book[] {
  const orderByTitle = order && order.includes("title") ? order : undefined;

  let orderedMenu: Book[] = [];
  const books = parse(jsonDbPath, defaultBooks);
  if (orderByTitle)
    orderedMenu = [...books].sort((a, b) => a.title.localeCompare(b.title));

  if (orderByTitle === "-title") orderedMenu = orderedMenu.reverse();

  return orderedMenu.length === 0 ? books : orderedMenu;
}

function readBookById(id: number): Book | undefined {
  const books = parse(jsonDbPath, defaultBooks);
  return books.find((book) => book.id === id);
}

function createBook(newBook: NewBook): Book {
  const books = parse(jsonDbPath, defaultBooks);
  const lastId = books[books.length - 1].id;
  const book: Book = { id: lastId + 1, ...newBook };
  const updatedBooks = [...books, book];
  serialize(jsonDbPath, updatedBooks);
  return book;
}

function deleteBook(id: number): Book | undefined {
  const books = parse(jsonDbPath, defaultBooks);
  const index = books.findIndex((book) => book.id === id);
  if (index === -1) return undefined;

  const deletedElements = books.splice(index, 1);
  serialize(jsonDbPath, books);
  return deletedElements[0];
}

function updateBook(
  id: number,
  updatedBook: Partial<NewBook>
): Book | undefined {
  const books = parse(jsonDbPath, defaultBooks);
  const book = books.find((book) => book.id === id);
  if (!book) return undefined;

  if (updatedBook.title !== undefined) {
    book.title = updatedBook.title;
  }
  if (updatedBook.image !== undefined) {
    book.image = updatedBook.image;
  }

  serialize(jsonDbPath, books);
  return book;
}

export { readAllBooks, readBookById, createBook, deleteBook, updateBook };
// ...existing code...