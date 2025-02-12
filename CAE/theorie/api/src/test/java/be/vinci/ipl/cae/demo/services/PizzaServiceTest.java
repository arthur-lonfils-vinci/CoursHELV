package be.vinci.ipl.cae.demo.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import be.vinci.ipl.cae.demo.models.entities.Pizza;
import be.vinci.ipl.cae.demo.repositories.PizzaRepository;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) // Mandatory to use Mockito annotations to initialize mocks
class PizzaServiceTest {

  @Mock
  private PizzaRepository pizzaRepository; // Creates a mock implementation of the specified class or interface.

  @InjectMocks
  private PizzaService pizzaService; // Creates an instance of the class and injects the mocks created with @Mock into it.

  @Test
  void readAllPizzasNoOrderWithNull() {
    // Arrange
    Pizza pizza1 = new Pizza();
    pizza1.setTitle("Margherita");
    Pizza pizza2 = new Pizza();
    pizza2.setTitle("Pepperoni");

    when(pizzaRepository.findAll()).thenReturn(Arrays.asList(pizza1, pizza2));

    // Act
    Iterable<Pizza> result = pizzaService.readAllPizzas(null);

    // Assert
    assertEquals(Arrays.asList(pizza1, pizza2), result);
  }

  @Test
  void readAllPizzasNoOrderWithWrongOrder() {
    // Arrange
    Pizza pizza1 = new Pizza();
    pizza1.setTitle("Margherita");
    Pizza pizza2 = new Pizza();
    pizza2.setTitle("Pepperoni");

    when(pizzaRepository.findAll()).thenReturn(Arrays.asList(pizza1, pizza2));

    // Act
    Iterable<Pizza> result = pizzaService.readAllPizzas("wrongAurder");

    // Assert
    assertEquals(Arrays.asList(pizza1, pizza2), result);
  }

  @Test
  void readAllPizzasOrderedByTitleAsc() {
    // Arrange
    Pizza pizza1 = new Pizza();
    pizza1.setTitle("Margherita");
    Pizza pizza2 = new Pizza();
    pizza2.setTitle("Pepperoni");
    Pizza pizza3 = new Pizza();
    pizza3.setTitle("Americana");

    when(pizzaRepository.findAllByOrderByTitleAsc()).thenReturn(
        Arrays.asList(pizza3, pizza1, pizza2));

    // Act
    Iterable<Pizza> result = pizzaService.readAllPizzas("title");

    // Assert
    assertEquals(Arrays.asList(pizza3, pizza1, pizza2), result);
  }

  @Test
  void readAllPizzasOrderedByTitleDesc() {
    // Arrange
    Pizza pizza1 = new Pizza();
    pizza1.setTitle("Margherita");
    Pizza pizza2 = new Pizza();
    pizza2.setTitle("Pepperoni");
    Pizza pizza3 = new Pizza();
    pizza3.setTitle("Americana");

    when(pizzaRepository.findAllByOrderByTitleDesc()).thenReturn(
        Arrays.asList(pizza2, pizza1, pizza3));

    // Act
    Iterable<Pizza> result = pizzaService.readAllPizzas("-title");

    // Assert
    assertEquals(Arrays.asList(pizza2, pizza1, pizza3), result);
  }

}