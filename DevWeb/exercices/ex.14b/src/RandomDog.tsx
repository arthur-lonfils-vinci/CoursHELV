import "./App.css";
import { useState, useEffect } from "react";

interface RandomDogProps {
  key: number;
  message: string;
}

function RandomDog() {
  const [dog, setDog] = useState<RandomDogProps>();


  useEffect(() => {
    const interval = setInterval(() => {
      fetchDog();
    }, 5000);

    return () => {
      clearInterval(interval);
    };
  }, []);

  const fetchDog = async () => {
    try {
      const response = await fetch("https://dog.ceo/api/breeds/image/random");

      if (!response.ok)
        throw new Error(
          `fetch error : ${response.status} : ${response.statusText}`
        );

      const dog = await response.json();
      setDog(dog);
    } catch (err) {
      console.error("HomePage::error: ", err);
    }
  }

  return (
    <>
      {dog && (
        <div key={dog.key} style={{ display: "flex", padding: "10px" }}>
          <img src={dog.message} alt="random dog" style={{ width: "200px" }} />
        </div>
      )}
    </>
  );
}

export default RandomDog;
