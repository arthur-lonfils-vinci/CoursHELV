import { useState, useEffect } from "react";
import "./App.css";
import RandomDog from "./RandomDog";
import { Data } from "./types.ts";

function App() {
  const [data, setData] = useState<Data[]>([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = () => {
    const urls = [
      "https://dog.ceo/api/breeds/image/random",
      "https://dog.ceo/api/breeds/image/random",
      "https://dog.ceo/api/breeds/image/random",
    ];

    Promise.all(
      urls.map((url) =>
        fetch(url).then((response) => {
          if (!response.ok)
            throw new Error(
              `fetch error : ${response.status} : ${response.statusText}`
            );
          return response.json();
        })
      )
    )
      .then((results) => setData(results))
      .catch((err) => {
        console.error("HomePage::error: ", err);
      });
  };

  console.log(data);

  return (
    <div className="App">
        <h1>Random Dogs</h1>
        <div className="random-dogs" style={{display: "flex", width: "200px", justifyContent: "center"}}>
          {data.map((dog) => (
            <RandomDog key={dog.id} img={dog.message} />
          ))}
        </div>
        <button onClick={fetchData}>Fetch</button>
    </div>
  );
}

export default App;
