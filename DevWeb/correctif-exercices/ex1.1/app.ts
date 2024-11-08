import express from "express";

import pizzaRouter from "./routes/films";

const app = express();

app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.use("/films", pizzaRouter);

export default app;
