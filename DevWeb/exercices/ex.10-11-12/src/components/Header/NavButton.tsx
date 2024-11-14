import { Button } from "@mui/material";
import { useNavigate, useLocation } from "react-router-dom";

interface NavButtonProps {
    menuButtonNames: string[];   
}

const NavButton = (props: NavButtonProps) => {
  const navigate = useNavigate();
  const location = useLocation();

  interface IsActive {
    (path: string): boolean;
  }

  const isActive: IsActive = (path) => location.pathname === path;

  return (
    <>
      {props.menuButtonNames.map((name, index) => (
        <Button
          key={index}
          onClick={() => navigate(`/${name.toLowerCase() == "home" ? "" : name.toLowerCase()+"page"}`)}
          variant={isActive(`/${name.toLowerCase() == "home" ? "" : name.toLowerCase()+"page"}`) ? "contained" : "outlined"}
          color={isActive(`/${name.toLowerCase() == "home" ? "" : name.toLowerCase()+"page"}`) ? "secondary" : "inherit"}
          sx={{
            borderRadius: 3,
            padding: "8px 16px",
            fontWeight: isActive(`/${name.toLowerCase() == "home" ? "" : name.toLowerCase()+"page"}`) ? "bold" : "normal",
            "&:hover": { backgroundColor: "#f0f0f0", color: "black" },
          }}
        >
          {name}
        </Button>
      ))}
    </>
  );
};

export default NavButton;
