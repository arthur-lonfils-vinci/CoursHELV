import { createTheme } from "@mui/material/styles";

const theme = {
  light: createTheme({
    palette: {
      mode: "light",
      primary: {
        light: "#ffcc80",
        main: "#ff9800",
        dark: "#f57c00",
        contrastText: "#000",
      },
      secondary: {
        light: "#b2ebf2",
        main: "#00bcd4",
        dark: "#00838f",
        contrastText: "#000",
      },
    },
  }),
  dark: createTheme({
    palette: {
      mode: "dark",
      primary: {
        light: "#ffcc80",
        main: "#ff9800",
        dark: "#000000",
        contrastText: "#ffffff",
      },
      secondary: {
        light: "#12E8E1",
        main: "#00bcd4",
        dark: "#000",
        contrastText: "#fff",
      },
    },
  }),
};

export default theme;