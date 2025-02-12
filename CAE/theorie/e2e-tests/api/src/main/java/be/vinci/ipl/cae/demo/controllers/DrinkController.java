package be.vinci.ipl.cae.demo.controllers;

import be.vinci.ipl.cae.demo.models.dtos.NewDrink;
import be.vinci.ipl.cae.demo.models.entities.Drink;
import be.vinci.ipl.cae.demo.services.DrinkService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * DrinkController to handle drink requests.
 */
@RestController
@RequestMapping("/drinks")
public class DrinkController {

  private final DrinkService drinkService;

  /**
   * Constructor for DrinkController.
   *
   * @param drinkService the injected DrinkService.
   */
  public DrinkController(DrinkService drinkService) {
    this.drinkService = drinkService;
  }

  /**
   * Get all drinks.
   *
   * @param budgetMax the maximum budget.
   * @return the list of drinks.
   */
  @GetMapping({"", "/"})
  public Iterable<Drink> getDrinks(
      @RequestParam(name = "budget-max", required = false) Double budgetMax) {
    if (budgetMax != null) {
      return drinkService.readAllDrinksWithinBudget(budgetMax);
    }
    return drinkService.readAllDrinks();
  }

  /**
   * Get a drink by id.
   *
   * @param id the drink id.
   * @return the drink.
   */
  @GetMapping("/{id}")
  public Drink getDrink(@PathVariable(name = "id") long id) {
    Drink drink = drinkService.readOneDrink(id);
    if (drink == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return drink;
  }

  /**
   * Add a drink.
   *
   * @param newDrink the new drink.
   * @return the added drink.
   */
  @PostMapping({"", "/"})
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public Drink addDrink(@RequestBody NewDrink newDrink) {
    if (newDrink == null
        || newDrink.getTitle() == null
        || newDrink.getTitle().isBlank()
        || newDrink.getImage() == null
        || newDrink.getImage().isBlank()
        || newDrink.getVolume() <= 0
        | newDrink.getPrice() <= 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    return drinkService.createOneDrink(newDrink);
  }

  /**
   * Delete a drink.
   *
   * @param id the drink id.
   * @return the deleted drink.
   */
  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public Drink deleteDrink(@PathVariable(name = "id") long id) {
    Drink drink = drinkService.deleteOneDrink(id);
    if (drink == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return drink;
  }

  /**
   * Update a drink.
   *
   * @param id       the drink id.
   * @param newDrink the new drink.
   * @return the updated drink.
   */
  @PatchMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public Drink updateDrink(@PathVariable(name = "id") long id, @RequestBody NewDrink newDrink) {
    if (newDrink == null
        || (newDrink.getTitle() != null && newDrink.getTitle().isBlank())
        || (newDrink.getImage() != null && newDrink.getImage().isBlank())
        || newDrink.getVolume() < 0.0
        || newDrink.getPrice() < 0.0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    Drink drink = drinkService.updateOneDrink(id, newDrink);
    if (drink == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return drink;
  }

}
