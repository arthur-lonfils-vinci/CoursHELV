package be.vinci.cae.fiche2.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "foodtrucks")
public class FoodTruck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "foodTruck", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Drink> drinks = new ArrayList<>();

    // Constructeur avec paramètres
    public FoodTruck(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // Constructeur par défaut
    public FoodTruck() {}
}
