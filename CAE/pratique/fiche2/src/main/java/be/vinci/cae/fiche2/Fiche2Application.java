package be.vinci.cae.fiche2;

import be.vinci.cae.fiche2.models.Drink;
import be.vinci.cae.fiche2.models.FoodTruck;
import be.vinci.cae.fiche2.repositories.DrinksRepository;
import be.vinci.cae.fiche2.repositories.FoodTrucksRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Fiche2Application {

	public static void main(String[] args) {
		SpringApplication.run(Fiche2Application.class, args);
	}

	@Bean
	public CommandLineRunner drinks(DrinksRepository drinksRepository, FoodTrucksRepository foodTrucksRepository) {
		return (args) -> {

			FoodTruck truck1 = foodTrucksRepository.save(new FoodTruck("Chez Momo", "Quartier Saint Boniface"));
			FoodTruck truck2 = foodTrucksRepository.save(new FoodTruck("Ardennes", "Arlon et environs"));

			drinksRepository.save(new Drink("Bloody Mary", "Yum totmatoes", 10.0f, true, truck1));
			drinksRepository.save(new Drink("Mojito", "Yum mint", 8.0f, true, truck1));
			drinksRepository.save(new Drink("Coca", "Yum sugar", 2.0f, false, truck1));
			drinksRepository.save(new Drink("Water", "Yum water", 0.0f, false, truck2));
		};
	}

}
