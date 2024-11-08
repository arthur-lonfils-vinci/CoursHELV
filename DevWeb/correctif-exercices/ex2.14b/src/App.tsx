import RandomDog from "./RandomDog";

const App = () => {
  return (
    <>
      <div style={{ display: "flex", flexDirection: "row", gap: 10 }}>
        <RandomDog />
        <RandomDog />
        <RandomDog />
      </div>
    </>
  );
};

export default App;
