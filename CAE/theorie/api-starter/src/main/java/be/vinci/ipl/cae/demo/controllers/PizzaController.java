package be.vinci.ipl.cae.demo.controllers;

import be.vinci.ipl.cae.demo.models.dtos.NewPizza;
import be.vinci.ipl.cae.demo.models.entities.Pizza;
import be.vinci.ipl.cae.demo.services.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping({"", "/"})
    public Iterable<Pizza> getPizzas(@RequestParam(required = false) String order) {
        return pizzaService.readAllPizzas(order);
    }

    @GetMapping("/{id}")
    public Pizza getPizza(@PathVariable long id) {
        Pizza pizza = pizzaService.readPizzaById(id);
        if (pizza == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return pizza;
    }

    @PostMapping({"", "/"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Pizza addPizza(@RequestBody NewPizza newPizza) {
        if (newPizza == null ||
                newPizza.getTitle() == null ||
                newPizza.getTitle().isBlank() ||
                newPizza.getContent() == null ||
                newPizza.getContent().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return pizzaService.createPizza(newPizza);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Pizza deletePizza(@PathVariable long id) {
        Pizza pizza = pizzaService.deletePizza(id);
        if (pizza == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return pizza;
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Pizza updatePizza(@PathVariable long id, @RequestBody NewPizza newPizza) {
        if (newPizza == null ||
                (newPizza.getTitle() != null && newPizza.getTitle().isBlank()) ||
                (newPizza.getContent() != null && newPizza.getContent().isBlank())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Pizza pizza = pizzaService.updatePizza(id, newPizza);
        if (pizza == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return pizza;
    }

}
