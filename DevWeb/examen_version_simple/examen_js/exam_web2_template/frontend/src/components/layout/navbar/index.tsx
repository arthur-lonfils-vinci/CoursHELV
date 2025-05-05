
import { useNavigate } from 'react-router-dom';

export const Menu = () => {
    const navigate = useNavigate();

    return (
      <nav>
        <button onClick={() => navigate("/")}>Home</button>
        <button onClick={() => navigate("/library")}>Library</button>
      </nav>
    );
};