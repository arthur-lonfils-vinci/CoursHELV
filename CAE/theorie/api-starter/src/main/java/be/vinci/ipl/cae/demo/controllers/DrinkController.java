package be.vinci.ipl.cae.demo.controllers;

import be.vinci.ipl.cae.demo.models.dtos.NewDrink;
import be.vinci.ipl.cae.demo.models.entities.Drink;
import be.vinci.ipl.cae.demo.services.DrinkService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/drinks")
public class DrinkController {

    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping({"", "/"})
    public Iterable<Drink> getDrinks(@RequestParam(name = "budget-max", required = false) Double budgetMax) {
        if (budgetMax != null) return drinkService.readAllDrinksWithinBudget(budgetMax);
        return drinkService.readAllDrinks();
    }

    @GetMapping("/{id}")
    public Drink getDrink(@PathVariable(name = "id") long id) {
        Drink drink = drinkService.readOneDrink(id);
        if (drink == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return drink;
    }

    @PostMapping({"", "/"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Drink addDrink(@RequestBody NewDrink newDrink) {
        if (newDrink == null ||
                newDrink.getTitle() == null ||
                newDrink.getTitle().isBlank() ||
                newDrink.getImage() == null ||
                newDrink.getImage().isBlank() ||
                newDrink.getVolume() <= 0 ||
                newDrink.getPrice() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return drinkService.createOneDrink(newDrink);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Drink deleteDrink(@PathVariable(name = "id") long id) {
        Drink drink = drinkService.deleteOneDrink(id);
        if (drink == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return drink;
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Drink updateDrink(@PathVariable(name = "id") long id, @RequestBody NewDrink newDrink) {
        if (newDrink == null ||
                (newDrink.getTitle() != null && newDrink.getTitle().isBlank()) ||
                (newDrink.getImage() != null && newDrink.getImage().isBlank()) ||
                newDrink.getVolume() < 0.0 ||
                newDrink.getPrice() < 0.0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Drink drink = drinkService.updateOneDrink(id, newDrink);
        if (drink == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return drink;
    }

}
