import { useEffect, useState } from "react";

interface Joke {
  joke: string;
  category: string;
}

const App = () => {
  const [joke, setJoke] = useState<Joke | undefined>(undefined);

  useEffect(() => {
    fetch("https://v2.jokeapi.dev/joke/Any?type=single")
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        setJoke({
          joke: data.joke ?? "No joke found",
          category: data.category ?? "Unknown",
        });
      });
  }, []);

  if (!joke) {
    return <p>Loading...</p>;
  }

  return (
    <div>
      <h3>Random joke</h3>
      <h4>{joke.category}</h4>
      <blockquote cite="https://www.huxley.net/bnw/four.html">
        <p>{joke.joke}</p>
      </blockquote>
      <p>
        <cite>https://v2.jokeapi.dev/joke.category</cite>
      </p>
    </div>
  );
};

export default App;
