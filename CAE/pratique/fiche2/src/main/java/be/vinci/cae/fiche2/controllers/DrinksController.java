package be.vinci.cae.fiche2.controllers;
import be.vinci.cae.fiche2.models.Drink;
import be.vinci.cae.fiche2.models.FoodTruck;
import be.vinci.cae.fiche2.services.DrinksService;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/drinks")
public class DrinksController {
    private DrinksService drinksService;

    public DrinksController(DrinksService drinksService) {
        this.drinksService = drinksService;
    }

    @GetMapping(value = {"", "/"})
    public Iterable<Drink> getDrinks(@RequestParam(required = false) String name) {
        if (name != null) {
            Drink drink = drinksService.getDrinkByName(name);
            return drink != null ? List.of(drink) : List.of();
        }
        return drinksService.getAllDrinks();
    }


    @GetMapping("/{id}")
    public Drink getDrinkById(@PathVariable long id) {
        return drinksService.getDrinkById(id);
    }

    // POST /drinks: Create a new drink
    @PostMapping("/")
    public Drink createDrink(@RequestBody Drink drink) {
        return drinksService.createDrink(drink);
    }

    // PUT /drinks/{id}: Update a drink by its ID
    @PutMapping("/{id}")
    public Drink updateDrink(@PathVariable Long id, @RequestBody Drink drink) {
        return drinksService.updateDrink(id, drink);
    }

    // DELETE /drinks/{id}: Delete a drink by its ID
    @DeleteMapping("/{id}")
    public void deleteDrink(@PathVariable Long id) {
        drinksService.deleteDrink(id);
    }

}