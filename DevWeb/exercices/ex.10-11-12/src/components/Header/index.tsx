import { Box, Container } from "@mui/material";

interface HeaderProps {
  urlLogo: string;
}

const Header = (props: HeaderProps) => {
  //const theme = useTheme();

  return (
    <Box>
      <Container>
        <Box sx={{ display: "flex"}}>
          <Box>
            <img src={props.urlLogo} alt="logo" style={{ width: "235px", height: "150px"}} />
          </Box>

          
        </Box>
      </Container>
    </Box>
  );
};

export default Header;
