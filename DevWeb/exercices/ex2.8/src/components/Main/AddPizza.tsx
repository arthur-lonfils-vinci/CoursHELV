import { useState, SyntheticEvent } from "react";
import { NewPizza } from "../../types";

interface AddPizzaProps {
  addPizza: (pizza: NewPizza) => void;
}

const AddPizza = ({ addPizza }: AddPizzaProps) => {
  const [pizza, setPizza] = useState("");
  const [description, setDescription] = useState("");

  const handleSubmit = (e: SyntheticEvent) => {
    e.preventDefault();
    console.log("submit:", pizza, description);
    addPizza({ title: pizza, content: description });
  };

  const handlePizzaChange = (e: SyntheticEvent) => {
    const pizzaInput = e.target as HTMLInputElement;
    console.log("change in pizzaInput:", pizzaInput.value);
    setPizza(pizzaInput.value);
  };

  const handleDescriptionChange = (e: SyntheticEvent) => {
    const descriptionInput = e.target as HTMLInputElement;
    console.log("change in descriptionInput:", descriptionInput.value);
    setDescription(descriptionInput.value);
  };

  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor="pizza">Pizza</label>
      <input type="text" value={pizza} onChange={handlePizzaChange} />
      <label htmlFor="description">Description</label>
      <input
        type="text"
        value={description}
        onChange={handleDescriptionChange}
      />
      <button type="submit">Add Pizza</button>
    </form>
  );
};

export default AddPizza;
