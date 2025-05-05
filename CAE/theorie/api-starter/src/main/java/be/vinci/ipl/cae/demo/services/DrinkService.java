package be.vinci.ipl.cae.demo.services;

import be.vinci.ipl.cae.demo.models.dtos.NewDrink;
import be.vinci.ipl.cae.demo.models.entities.Drink;
import be.vinci.ipl.cae.demo.repositories.DrinkRepository;
import org.springframework.stereotype.Service;

@Service
public class DrinkService {

    private final DrinkRepository drinkRepository;

    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public Iterable<Drink> readAllDrinks() {
        return drinkRepository.findAll();
    }

    public Iterable<Drink> readAllDrinksWithinBudget(double budget) {
        return drinkRepository.findAllByPriceLessThanEqual(budget);
    }

    public Drink readOneDrink(Long id) {
        return drinkRepository.findById(id).orElse(null);
    }

    public Drink createOneDrink(NewDrink newDrink) {
        Drink drink = new Drink();
        drink.setTitle(newDrink.getTitle());
        drink.setImage(newDrink.getImage());
        drink.setVolume(newDrink.getVolume());
        drink.setPrice(newDrink.getPrice());
        return drinkRepository.save(drink);
    }

    public Drink deleteOneDrink(Long id) {
        Drink drink = drinkRepository.findById(id).orElse(null);
        if (drink != null) drinkRepository.delete(drink);
        return drink;
    }

    public Drink updateOneDrink(long id, NewDrink newDrink) {
        Drink drink = drinkRepository.findById(id).orElse(null);
        if (drink == null) return null;

        if (newDrink.getTitle() != null) drink.setTitle(newDrink.getTitle());
        if (newDrink.getImage() != null) drink.setImage(newDrink.getImage());
        if (newDrink.getVolume() > 0.0) drink.setVolume(newDrink.getVolume());
        if (newDrink.getPrice() > 0.0) drink.setPrice(newDrink.getPrice());

        return drinkRepository.save(drink);
    }

}
