import "./App.css";
import { useState, useEffect } from "react";

interface RandomDogProps {
  key: number;
  message: string;
}

function RandomDog(method: any) {
    const [dog, setDog] = useState<RandomDogProps>();

    useEffect(() => {
        fetch("https://dog.ceo/api/breeds/image/random")
          .then((response) => {
            if (!response.ok)
              throw new Error(
                `fetch error : ${response.status} : ${response.statusText}`
              );
            return response.json();
          })
          .then((dog) => setDog(dog))
          .catch((err) => {
            console.error("HomePage::error: ", err);
          });
      }, [method]);

  return (
    <>
        {dog && (
          <div key={dog.key} style={{display: "flex", padding: "10px"}}>
            <img src={dog.message} alt="random dog" style={{ width: "200px" }}/>  
          </div>
        )}
    </>
  );
}

export default RandomDog;
