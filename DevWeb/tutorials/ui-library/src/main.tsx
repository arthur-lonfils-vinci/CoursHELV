import CssBaseline from "@mui/material/CssBaseline";
import "@fontsource/roboto/700.css";
import { ThemeProvider } from "@mui/material/styles";
import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './components/App/index.tsx'
import theme from './themes.ts'

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <ThemeProvider theme={theme.dark}>
      <CssBaseline />
      <App />
    </ThemeProvider>
  </React.StrictMode>,
)
