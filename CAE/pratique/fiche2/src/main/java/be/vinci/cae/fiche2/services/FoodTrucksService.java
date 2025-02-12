package be.vinci.cae.fiche2.services;

import be.vinci.cae.fiche2.models.FoodTruck;
import be.vinci.cae.fiche2.repositories.FoodTrucksRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodTrucksService {
    private final FoodTrucksRepository foodTrucksRepository;

    public FoodTrucksService(FoodTrucksRepository foodTrucksRepository) {
        this.foodTrucksRepository = foodTrucksRepository;
    }

    public Iterable<FoodTruck> getAllFoodTrucks() {
        return foodTrucksRepository.findAll();
    }

    public FoodTruck getFoodTruckById(Long id) {
        Optional<FoodTruck> foodTruck = foodTrucksRepository.findById(id);
        return foodTruck.orElseThrow(() -> new RuntimeException("FoodTruck not found with id: " + id));
    }

    public FoodTruck getFoodTruckByName(String name) {
        return foodTrucksRepository.findByName(name);
    }

    public FoodTruck createFoodTruck(FoodTruck foodTruck) {
        return foodTrucksRepository.save(foodTruck);
    }

    public FoodTruck updateFoodTruck(Long id, FoodTruck foodTruck) {
        FoodTruck foodTruckToUpdate = getFoodTruckById(id);
        foodTruckToUpdate.setName(foodTruck.getName());
        foodTruckToUpdate.setAddress(foodTruck.getAddress());
        return foodTrucksRepository.save(foodTruckToUpdate);
    }

    public void deleteFoodTruck(Long id) {
        foodTrucksRepository.deleteById(id);
    }


}
