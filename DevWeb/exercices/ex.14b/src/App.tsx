import "./App.css";
import RandomDog from "./RandomDog";

function App() {
  return (
    <div className="App">
        <h1>Random Dogs</h1>
        <div className="random-dogs" style={{display: "flex", width: "200px", justifyContent: "center"}}>
            <RandomDog />
            <RandomDog />
            <RandomDog />
        </div>
    </div>
  );
}

export default App;
