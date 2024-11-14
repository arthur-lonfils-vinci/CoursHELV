import { Box, Container, Typography, useTheme } from "@mui/material";

interface FooterProps {
  urlLogo: string;
  children: React.ReactNode;
}

const Footer = (props: FooterProps) => {
  const theme = useTheme();
  return (
    <Box>
      <Container>
        <Box sx={{ display: "flex", flexDirection: "row" }}>
          <img src={props.urlLogo} alt="logo" style={{ width: "100px" }} />
          <Typography
            variant="h4"
            style={{ color: theme.palette.primary.light }}
          >
            {props.children}
          </Typography>
        </Box>
      </Container>
    </Box>
  );
};

export default Footer;
