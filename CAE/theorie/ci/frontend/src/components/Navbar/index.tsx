import { useNavigate } from 'react-router-dom';
import { useContext } from 'react';
import { AppBar, Toolbar, Button, Typography } from '@mui/material';
import { UserContext } from '../../contexts/UserContext';
import { UserContextType } from '../../types';

const NavBar = () => {
  const { authenticatedUser, clearUser } =
    useContext<UserContextType>(UserContext);
  const navigate = useNavigate();

  return (
    <AppBar position="static">
      <Toolbar>
        <Button color="inherit" onClick={() => navigate('/')}>
          Home
        </Button>
        {authenticatedUser ? (
          <>
            <Button color="inherit" onClick={() => navigate('/add-pizza')}>
              Ajouter une pizza
            </Button>
            <Button color="inherit" onClick={() => clearUser()}>
              Se déconnecter
            </Button>
            <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
              Hello dear {authenticatedUser.username}
            </Typography>
          </>
        ) : (
          <>
            <Button color="inherit" onClick={() => navigate('/register')}>
              Créer un utilisateur
            </Button>
            <Button color="inherit" onClick={() => navigate('/login')}>
              Se connecter
            </Button>
          </>
        )}
      </Toolbar>
    </AppBar>
  );
};

export default NavBar;
