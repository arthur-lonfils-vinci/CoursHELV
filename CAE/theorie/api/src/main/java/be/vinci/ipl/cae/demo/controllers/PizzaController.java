package be.vinci.ipl.cae.demo.controllers;

import be.vinci.ipl.cae.demo.models.dtos.NewPizza;
import be.vinci.ipl.cae.demo.models.entities.Pizza;
import be.vinci.ipl.cae.demo.services.PizzaService;
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
 * PizzaController to handle pizza requests.
 */
@RestController
@RequestMapping("/pizzas")
public class PizzaController {

  private final PizzaService pizzaService;

  /**
   * Constructor for PizzaController.
   *
   * @param pizzaService the injected PizzaService.
   */
  public PizzaController(PizzaService pizzaService) {
    this.pizzaService = pizzaService;
  }

  /**
   * Get all pizzas.
   *
   * @param order the order.
   * @return the list of pizzas.
   */
  @GetMapping({"", "/"})
  public Iterable<Pizza> getPizzas(@RequestParam(required = false) String order) {
    return pizzaService.readAllPizzas(order);
  }

  /**
   * Get a pizza by id.
   *
   * @param id the pizza id.
   * @return the pizza.
   */
  @GetMapping("/{id}")
  public Pizza getPizza(@PathVariable long id) {
    Pizza pizza = pizzaService.readPizzaById(id);
    if (pizza == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return pizza;
  }

  private boolean isInvalidPizza(NewPizza newPizza) {
    return newPizza == null
        || newPizza.getTitle() == null
        || newPizza.getTitle().isBlank()
        || newPizza.getContent() == null
        || newPizza.getContent().isBlank();
  }

  /**
   * Add a pizza.
   *
   * @param newPizza the new pizza.
   * @return the pizza.
   */
  @PostMapping({"", "/"})
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public Pizza addPizza(@RequestBody NewPizza newPizza) {
    if (isInvalidPizza(newPizza)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    return pizzaService.createPizza(newPizza);
  }

  /**
   * Delete a pizza by id.
   *
   * @param id the pizza id.
   * @return the pizza.
   */
  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public Pizza deletePizza(@PathVariable long id) {
    Pizza pizza = pizzaService.deletePizza(id);
    if (pizza == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return pizza;
  }

  /**
   * Update a pizza.
   *
   * @param id       the pizza id.
   * @param newPizza the new pizza.
   * @return the pizza.
   */
  @PatchMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public Pizza updatePizza(@PathVariable long id, @RequestBody NewPizza newPizza) {
    if (isInvalidPizza(newPizza)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    Pizza pizza = pizzaService.updatePizza(id, newPizza);
    if (pizza == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return pizza;
  }

}
