import { AppBar, Toolbar } from "@mui/material";
import NavButton  from "./NavButton";

interface NavbarProps {
    menuButtonNames: string[];
}

const Navbar = (props: NavbarProps) => {

    return (
        <AppBar position="static" color="transparent" sx={{ boxShadow: "none", borderBottom: "1px solid #ddd" }}>
            <Toolbar sx={{ display: "flex", justifyContent: "center", gap: 2 }}>
                <NavButton menuButtonNames={props.menuButtonNames}/>
            </Toolbar>
        </AppBar>
    );
};

export default Navbar;
