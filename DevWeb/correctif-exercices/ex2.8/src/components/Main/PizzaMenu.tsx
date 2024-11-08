import "./PizzaMenu.css";
import { Pizza } from "../../types";

interface PizzaMenuProps {
  pizzas: Pizza[];
}

const PizzaMenu = ({ pizzas }: PizzaMenuProps) => {
  return (
    <table className="pizza-menu">
      <thead>
        <tr>
          <th>Pizza</th>
          <th>Description</th>
        </tr>
      </thead>
      <tbody>
        {pizzas.map((pizza) => (
          <tr key={pizza.id}>
            <td>{pizza.title}</td>
            <td>{pizza.content}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default PizzaMenu;
export type { Pizza };
