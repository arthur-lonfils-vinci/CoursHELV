package be.vinci.ipl.cae.demo.services;

import be.vinci.ipl.cae.demo.models.dtos.NewPizza;
import be.vinci.ipl.cae.demo.models.entities.Pizza;
import be.vinci.ipl.cae.demo.repositories.PizzaRepository;
import org.springframework.stereotype.Service;

/**
 * Pizza service.
 */
@Service
public class PizzaService {

  private final PizzaRepository pizzaRepository;

  /**
   * Constructor.
   *
   * @param pizzaRepository the pizza repository
   */
  public PizzaService(PizzaRepository pizzaRepository) {
    this.pizzaRepository = pizzaRepository;
  }

  /**
   * Read all pizzas.
   *
   * @param order if order contains "title" the pizzas will be ordered by title in ascending order
   *              if order contains "-title" the pizzas will be ordered by title in descending
   *              order.
   * @return the pizzas
   */
  public Iterable<Pizza> readAllPizzas(String order) {
    if (order == null || !order.contains("title")) {
      return pizzaRepository.findAll();
    }

    if (order.contains("-")) {
      return pizzaRepository.findAllByOrderByTitleDesc();
    }
    return pizzaRepository.findAllByOrderByTitleAsc();
  }

  /**
   * Read a pizza by its id.
   *
   * @param id the id
   * @return the pizza
   */
  public Pizza readPizzaById(Long id) {
    return pizzaRepository.findById(id).orElse(null);
  }

  /**
   * Create a pizza.
   *
   * @param newPizza the new pizza
   * @return the pizza
   */
  public Pizza createPizza(NewPizza newPizza) {
    Pizza pizza = new Pizza();
    pizza.setTitle(newPizza.getTitle());
    pizza.setContent(newPizza.getContent());
    return pizzaRepository.save(pizza);
  }

  /**
   * Delete a pizza by its id.
   *
   * @param id the id
   * @return the pizza
   */
  public Pizza deletePizza(Long id) {
    Pizza pizza = pizzaRepository.findById(id).orElse(null);
    if (pizza != null) {
      pizzaRepository.delete(pizza);
    }
    return pizza;
  }

  /**
   * Update a pizza.
   *
   * @param id       the id
   * @param newPizza the new pizza
   * @return the pizza
   */
  public Pizza updatePizza(Long id, NewPizza newPizza) {
    Pizza pizza = pizzaRepository.findById(id).orElse(null);
    if (pizza == null) {
      return null;
    }

    if (newPizza.getTitle() != null) {
      pizza.setTitle(newPizza.getTitle());
    }
    if (newPizza.getContent() != null) {
      pizza.setContent(newPizza.getContent());
    }

    return pizzaRepository.save(pizza);
  }

}
