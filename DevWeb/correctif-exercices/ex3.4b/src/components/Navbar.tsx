import { Link, useNavigate } from "react-router-dom";
import "./Navbar.css";
import { MaybeAuthenticatedUser } from "../types";

interface NavBarProps {
  authenticatedUser: MaybeAuthenticatedUser;
  clearUser: () => void;
}
const NavBar = ({ authenticatedUser, clearUser }: NavBarProps) => {
  const navigate = useNavigate();
  if (authenticatedUser) {
    return (
      <nav className="navbar">
        <Link to="/">Home</Link>
        <Link to="/cinemas">Cinemas</Link>
        <Link to="/movie-list">My favorite movies</Link>
        <Link to="/add-movie">Add a movie</Link>
        <a
          href="/"
          onClick={(e) => {
            e.preventDefault();
            clearUser();
            navigate("/login");
          }}
        >
          Logout
        </a>
      </nav>
    );
  }

  return (
    <nav className="navbar">
      <Link to="/">Home</Link>
      <Link to="/cinemas">Cinemas</Link>
      <Link to="/movie-list">My favorite movies</Link>
      <Link to="/login">Login</Link>
      <Link to="/register">Register</Link>
    </nav>
  );
};

export default NavBar;
