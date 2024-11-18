import { useState } from "react";
import "./App.css";
import RandomDog from "./RandomDog";

function App() {
  const [actionToBePerformed, setActionToBePerformed] = useState(false);
  const handleClick = () => {
    setActionToBePerformed(!actionToBePerformed);
  }

  return (
    <div className="App">
        <h1>Random Dogs</h1>
        <div className="random-dogs" style={{display: "flex", width: "200px", justifyContent: "center"}}>
            <RandomDog method={handleClick}/>
            <RandomDog method={handleClick}/>
            <RandomDog method={handleClick}/>
        </div>
        <button onClick={handleClick}>Refresh</button>
    </div>
  );
}

export default App;
