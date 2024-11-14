import { Box, Typography, useTheme } from "@mui/material";
import { Copyright } from "@mui/icons-material";
import Footer from "../Footer";
import Header from "../Header";
import { Outlet } from "react-router-dom";

function App() {
  const theme = useTheme();

  return (
    <Box
      sx={{
        py: 2,
        display: "flex",
        flexDirection: "column",
        height: "100%",
        alignItems: "center",
        backgroundColor: theme.palette.background.default,
      }}
    >
      <Header urlLogo="https://logodix.com/logo/2098363.png" />

      <Outlet />
      
      <Footer urlLogo="https://media.istockphoto.com/id/1202770152/fr/photo/bobine-de-film-disolement-sur-le-fond-jaune-lumineux-dans-les-couleurs-pastel.jpg?s=1024x1024&w=is&k=20&c=2yKBrC8oyimPdW-5IxFWN_zxFPVK3KWYL9OE2gVmVX4=">
        <Typography>
          <Copyright />
          MesFilms
        </Typography>
      </Footer>
    </Box>
  );
}

export default App;
