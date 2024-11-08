import express, { ErrorRequestHandler } from "express";
import cors from "cors";

import authsRouter from "./routes/auths";
import filmsRouter from "./routes/films";
import commentsRouter from "./routes/comments";


const app = express();

const corsOptions = {
  origin: [/^http:\/\/localhost/, "http://amazing.you.com"],
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


app.use("/auths", authsRouter);
app.use("/films", filmsRouter);
app.use("/comments", commentsRouter);


const errorHandler: ErrorRequestHandler = (err, _req, res, _next) => {
  console.error(err.stack);
  return res.status(500).send("Something broke!");
};

app.use(errorHandler);
export default app;
