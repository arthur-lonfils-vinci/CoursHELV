import { useEffect, useState } from "react";
import RandomDog from "./RandomDog";

interface Dog {
  message: string;
  status: string;
}

const App = () => {
  const [refresh, setRefresh] = useState(false);

  return (
    <>
      <div style={{ display: "flex", flexDirection: "row", gap: 10 }}>
        <RandomDog key={`${refresh}1`} />
        <RandomDog key={`${refresh}2`} />
        <RandomDog key={`${refresh}3`} />
      </div>

      <button
        onClick={() => setRefresh(!refresh)}
        style={{
          marginTop: "20px",
          padding: "10px 20px",
          fontSize: "1em",
          cursor: "pointer",
        }}
      >
        Refresh Dogs
      </button>
    </>
  );
};

export default App;
