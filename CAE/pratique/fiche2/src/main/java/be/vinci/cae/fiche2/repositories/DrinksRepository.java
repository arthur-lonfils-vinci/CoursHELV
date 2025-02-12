package be.vinci.cae.fiche2.repositories;

import be.vinci.cae.fiche2.models.Drink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinksRepository extends CrudRepository<Drink, Long> {
    Drink findByName(String name);
}
