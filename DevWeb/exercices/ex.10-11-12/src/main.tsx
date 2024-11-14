import CssBaseline from "@mui/material/CssBaseline";
import "@fontsource/roboto/700.css";
import { ThemeProvider } from "@mui/material/styles";
import React from "react";
import ReactDOM from "react-dom/client";
import App from "./components/App";
import theme from './themes.ts'
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import FavoriteMovie from "./components/FavoriteMovie";
import HomePage from "./components/Main";
import CinemaPage from "./components/CinemaPage";

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "",
        element: <HomePage />,
      },
      {
        path: "moviespage",
        element: <FavoriteMovie />,
      },
      {
        path: "cinemaspage",
        element: <CinemaPage />,
      }
    ],
  },
]);

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <ThemeProvider theme={theme.dark}>
      <CssBaseline />
      <RouterProvider router={router}/> 
    </ThemeProvider>
  </React.StrictMode>
);
