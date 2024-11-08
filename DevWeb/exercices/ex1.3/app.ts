import express from "express";
import movieRouter from "./routes/films";

const app = express();

app.use(express.json());
app.use(express.urlencoded({ extended: false }));


let counter = 0;
app.use((_req, _res, next) => {
    
    if(_req.method == "GET"){
      console.log("GET counter : " + ++counter);
    }
    next();
});

app.use("/films", movieRouter);

export default app;
