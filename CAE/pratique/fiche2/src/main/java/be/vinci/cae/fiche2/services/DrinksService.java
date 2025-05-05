package be.vinci.cae.fiche2.services;
import be.vinci.cae.fiche2.models.Drink;
import be.vinci.cae.fiche2.repositories.DrinksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrinksService {
    private final DrinksRepository drinksRepository;

    public DrinksService(DrinksRepository drinksRepository) {
        this.drinksRepository = drinksRepository;
    }

    public Iterable<Drink> getAllDrinks() {
        return drinksRepository.findAll();
    }

    public Drink getDrinkByName(String name) {
        return drinksRepository.findByName(name);
    }


    public Drink getDrinkById(Long id) {
        Optional<Drink> drink = drinksRepository.findById(id);
        return drink.orElseThrow(() -> new RuntimeException("Drink not found with id: " + id));
    }

    public Drink createDrink(Drink drink) {
        return drinksRepository.save(drink);
    }


    public Drink updateDrink(Long id, Drink drink) {
        Drink existingDrink = getDrinkById(id);
        existingDrink.setName(drink.getName());
        existingDrink.setDescription(drink.getDescription());
        existingDrink.setPrice(drink.getPrice());
        existingDrink.setAlcoholic(drink.getAlcoholic());
        return drinksRepository.save(existingDrink);
    }

    public void deleteDrink(Long id) {
        drinksRepository.deleteById(id);
    }

}
