import { useState, SyntheticEvent } from 'react';
import { useNavigate, useOutletContext } from 'react-router-dom';

import { PizzeriaContext } from '../../types';
import { Box, Button, TextField, useTheme } from '@mui/material';

const AddPizza = () => {
  const theme = useTheme();
  const { addPizza }: PizzeriaContext = useOutletContext();

  const navigate = useNavigate();
  const [pizza, setPizza] = useState('');
  const [description, setDescription] = useState('');

  const handleSubmit = (e: SyntheticEvent) => {
    e.preventDefault();
    addPizza({ title: pizza, content: description });
    navigate('/');
  };

  const handlePizzaChange = (e: SyntheticEvent) => {
    const pizzaInput = e.target as HTMLInputElement;
    console.log('change in pizzaInput:', pizzaInput.value);
    setPizza(pizzaInput.value);
  };

  const handleDescriptionChange = (e: SyntheticEvent) => {
    const descriptionInput = e.target as HTMLInputElement;
    console.log('change in descriptionInput:', descriptionInput.value);
    setDescription(descriptionInput.value);
  };

  return (
    <Box
      sx={{
        margin: 2,
        padding: 3,
        backgroundColor: 'secondary.light',
        borderRadius: 4,
        boxShadow: 2,
      }}
    >
      <form onSubmit={handleSubmit}>
        <Box sx={{ marginBottom: 2 }}>
          <TextField
            fullWidth
            id="pizza"
            name="pizza"
            label="Pizza"
            variant="outlined"
            value={pizza}
            onChange={handlePizzaChange}
            required
            color="primary"
            sx={{
              input: { color: theme.palette.secondary.contrastText },
            }}
          />
        </Box>
        <Box sx={{ marginBottom: 2 }}>
          <TextField
            fullWidth
            id="description"
            name="description"
            label="Description"
            variant="outlined"
            value={description}
            onChange={handleDescriptionChange}
            required
            color="primary"
            sx={{
              input: { color: theme.palette.secondary.contrastText },
            }}
          />
        </Box>
        <Button type="submit" variant="contained" color="primary">
          Ajouter
        </Button>
      </form>
    </Box>
  );
};

export default AddPizza;
