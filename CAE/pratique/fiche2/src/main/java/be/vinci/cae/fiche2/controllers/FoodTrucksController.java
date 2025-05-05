package be.vinci.cae.fiche2.controllers;

import be.vinci.cae.fiche2.models.FoodTruck;
import be.vinci.cae.fiche2.services.FoodTrucksService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/foodtrucks")
public class FoodTrucksController {
    private FoodTrucksService foodTrucksService;

    public FoodTrucksController(FoodTrucksService foodTrucksService) {
        this.foodTrucksService = foodTrucksService;
    }

    @GetMapping(value = {"", "/"})
    public Iterable<FoodTruck> getFoodTruck(@RequestParam(required = false) String name) {
        if (name != null) {
            FoodTruck foodTruck = foodTrucksService.getFoodTruckByName(name);
            return foodTruck != null ? List.of(foodTruck) : List.of();
        }
        return foodTrucksService.getAllFoodTrucks();
    }


    @GetMapping("/{id}")
    public FoodTruck getFoodTruckById(@PathVariable long id) {
        return foodTrucksService.getFoodTruckById(id);
    }

    // POST /drinks: Create a new drink
    @PostMapping("/")
    public FoodTruck createFoodTruck(@RequestBody FoodTruck foodTruck) {
        return foodTrucksService.createFoodTruck(foodTruck);
    }

    // PUT /drinks/{id}: Update a drink by its ID
    @PutMapping("/{id}")
    public FoodTruck updateFoodTruck(@PathVariable Long id, @RequestBody FoodTruck foodTruck) {
        return foodTrucksService.updateFoodTruck(id, foodTruck);
    }

    // DELETE /drinks/{id}: Delete a drink by its ID
    @DeleteMapping("/{id}")
    public void deleteFoodTruck(@PathVariable Long id) {
        foodTrucksService.deleteFoodTruck(id);
    }

}