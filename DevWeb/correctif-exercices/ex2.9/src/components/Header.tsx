// import "./Header.css";

import { AspectRatio } from "@mui/icons-material";
import { Box, useTheme } from "@mui/material";

interface HeaderProps {
  urlLogo: string;
  children: React.ReactNode;
}

const Header = (props: HeaderProps) => {
  const theme = useTheme();
  return (
    <Box
      component="header"
      sx={{
        backgroundColor: theme.palette.primary.dark,
        color: theme.palette.primary.contrastText,
      }}
    >
      <Box component="img" src={props.urlLogo} alt="logo" sx={{ height: 50 }} />

      <div>{props.children}</div>
    </Box>
  );
};

export default Header;
