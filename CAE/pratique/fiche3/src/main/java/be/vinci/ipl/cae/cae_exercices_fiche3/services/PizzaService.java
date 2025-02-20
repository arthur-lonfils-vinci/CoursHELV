package be.vinci.ipl.cae.cae_exercices_fiche3.services;

import be.vinci.ipl.cae.cae_exercices_fiche3.models.dtos.NewPizza;
import be.vinci.ipl.cae.cae_exercices_fiche3.models.entities.Pizza;
import be.vinci.ipl.cae.cae_exercices_fiche3.repositories.PizzaRepository;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public Iterable<Pizza> readAllPizzas(String order) {
        if (order == null || !order.contains("title")) return pizzaRepository.findAll();

        if (order.contains("-")) return pizzaRepository.findAllByOrderByTitleDesc();
        return pizzaRepository.findAllByOrderByTitleAsc();
    }

    public Pizza readPizzaById(Long id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    public Pizza createPizza(NewPizza newPizza) {
        Pizza pizza = new Pizza();
        pizza.setTitle(newPizza.getTitle());
        pizza.setContent(newPizza.getContent());
        return pizzaRepository.save(pizza);
    }

    public Pizza deletePizza(Long id) {
        Pizza pizza = pizzaRepository.findById(id).orElse(null);
        if (pizza != null) pizzaRepository.delete(pizza);
        return pizza;
    }

    public Pizza updatePizza(Long id, NewPizza newPizza) {
        Pizza pizza = pizzaRepository.findById(id).orElse(null);
        if (pizza == null) return null;

        if (newPizza.getTitle() != null) pizza.setTitle(newPizza.getTitle());
        if (newPizza.getContent() != null) pizza.setContent(newPizza.getContent());

        return pizzaRepository.save(pizza);
    }

}
