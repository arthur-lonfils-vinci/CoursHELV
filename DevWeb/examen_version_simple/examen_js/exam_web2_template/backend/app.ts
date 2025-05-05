import express, { ErrorRequestHandler } from "express";
import cors from "cors";

import usersRouter from "./routes/users";
import bookRouter from "./routes/books";
import authsRouter from "./routes/auths";


const app = express();

const corsOptions = {
  origin: [/^http:\/\/localhost(:[0-9]+)?$/, "http://amazing.you.com"],
  credentials: true,
  "Content-Type": "application/json"
};

app.use(cors(corsOptions));

app.use((_req, _res, next) => {
  console.log(
    "Time:",
    new Date().toLocaleString("fr-FR", { timeZone: "Europe/Brussels" })
  );
  next();
});

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.use("/users", usersRouter);
app.use("/books", bookRouter);
app.use("/auths", authsRouter);


const errorHandler: ErrorRequestHandler = (err, _req, res, _next) => {
  console.error(err.stack);
  return res.status(500).send("Something broke!");
};

app.use(errorHandler);
export default app;
