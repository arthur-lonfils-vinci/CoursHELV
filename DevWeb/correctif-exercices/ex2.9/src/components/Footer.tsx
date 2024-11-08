import { Box, useTheme } from "@mui/material";

interface FooterProps {
  urlLogo: string;
  children: React.ReactNode;
}

const Footer = (props: FooterProps) => {
  const theme = useTheme();
  return (
    <Box
      component="footer"
      sx={{
        backgroundColor: theme.palette.secondary.dark,
        color: theme.palette.secondary.contrastText,
      }}
    >
      <div>{props.children}</div>
      <Box component="img" src={props.urlLogo} alt="logo" sx={{ height: 50 }} />
    </Box>
  );
};

export default Footer;
