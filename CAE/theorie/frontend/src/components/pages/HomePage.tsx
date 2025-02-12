import sound from '../../assets/sounds/Infecticide-11-Pizza-Spinoza.mp3';
import AudioPlayer from '../AudioPlayer/AudioPlayer';
import PizzaMenu from '../PizzaMenu';
import DrinkMenu from '../DrinkMenu';
import { useOutletContext } from 'react-router-dom';
import { PizzeriaContext } from '../../types';
import { Container, Typography } from '@mui/material';

const HomePage = () => {
  const {
    actionToBePerformed,
    clearActionToBePerformed,
    pizzas,
    drinks,
  }: PizzeriaContext = useOutletContext();

  return (
    <Container component="main" sx={{ mt: 8, mb: 2, flex: '1' }} maxWidth="sm">
      <Typography variant="h2" component="h1" gutterBottom>
        My HomePage
      </Typography>
      <Typography variant="h5" component="h2" gutterBottom>
        Because we love JS, you can also click on the header to stop / start the
        music ; )
      </Typography>
      <AudioPlayer
        sound={sound}
        actionToBePerformed={actionToBePerformed}
        clearActionToBePerformed={clearActionToBePerformed}
      />

      <PizzaMenu pizzas={pizzas} />

      <DrinkMenu title="Notre Menu de Boissons" drinks={drinks} />
    </Container>
  );
};

export default HomePage;
