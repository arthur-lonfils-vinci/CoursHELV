package be.vinci.cae.fiche2.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "drinks")
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private float price;
    @Column(nullable = false)
    private Boolean alcoholic;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "foodtruck_id", nullable = false)
    private FoodTruck foodTruck;

    public Drink(String name, String description, float price, Boolean alcoholic, FoodTruck foodTruck) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.alcoholic = alcoholic;
        this.foodTruck = foodTruck;
    }

    public Drink() {}
}