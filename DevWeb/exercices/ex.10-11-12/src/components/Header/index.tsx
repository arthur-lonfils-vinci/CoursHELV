import { Box } from "@mui/material";
import Navbar from "./Navbar";

interface HeaderProps {
  urlLogo: string;
}

const menuButtonNames = ["Home", "Movies", "Cinemas"];

const Header = (props: HeaderProps) => {
  return (
    <Box display={"flex"} width={"90%"}>
        <Box flex="1" width= {"140px"}  height={"95px"}>
            <img
              src={props.urlLogo}
              alt="logo"
              style={{ width: "140px", height: "95px" }}
            />
        </Box>
        <Box flex="1">
        <Navbar menuButtonNames={menuButtonNames}/>
        </Box>  
    </Box>
  );
};

export default Header;
