import { Typography, Box } from "@mui/material";

interface PageTitleProps {
  title: string;
}

const PageTitle = (props: PageTitleProps) => {
  return (
    <Box sx={{
      py: 4,
      pl: 2,
    }}>
      <Typography variant="h4" component="h2" color="primary" gutterBottom>
        {props.title}
      </Typography>
    </Box>
  );
};

export default PageTitle;
