import "./App.css";

interface RandomDogProps {
  key: number;
  img: string;
}

function RandomDog({ key, img }: RandomDogProps) {

  return (
    <>
        <div key={key} style={{display: "flex", padding: "10px"}}>
          <img src={img} alt="random dog" style={{ width: "200px" }}/>  
        </div>
    </>
  );
}

export default RandomDog;
