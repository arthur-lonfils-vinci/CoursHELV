import { Box, Container } from "@mui/material";
import Navbar from "./Navbar";

interface HeaderProps {
  urlLogo: string;
}

const menuButtonNames = ["Home", "Movies", "Cinemas"];

const Header = (props: HeaderProps) => {
  return (
    <Box>
      <Container sx={{ display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center"}}>
        <Box sx={{width: "235px", height: "150px"}}>
            <img
              src={props.urlLogo}
              alt="logo"
              style={{ width: "235px", height: "150px" }}
            />
        </Box>
        <Navbar menuButtonNames={menuButtonNames}/>
      </Container>
    </Box>
  );
};

export default Header;
