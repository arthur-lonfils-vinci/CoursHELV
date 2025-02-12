package be.vinci.ipl.cae.demo.repositories;

import be.vinci.ipl.cae.demo.models.entities.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Pizza repository.
 */
@Repository
public interface PizzaRepository extends CrudRepository<Pizza, Long> {

  /**
   * Find all pizzas ordered by title ascending.
   *
   * @return the pizzas
   */
  Iterable<Pizza> findAllByOrderByTitleAsc();

  /**
   * Find all pizzas ordered by title descending.
   *
   * @return the pizzas
   */
  Iterable<Pizza> findAllByOrderByTitleDesc();
}
