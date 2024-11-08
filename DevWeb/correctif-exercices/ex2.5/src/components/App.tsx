import reactLogo from "../assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import ClickCounter from "./ClickCounter";

function App() {
  return (
    <>
      <div>
        <a href="https://vitejs.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <ClickCounter
        title="Amazing counter"
        on10ClickMessage="You are a master in the art of clicking !"
        onMouseOverMessage="Please click on me now !"
      />
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  );
}

export default App;
