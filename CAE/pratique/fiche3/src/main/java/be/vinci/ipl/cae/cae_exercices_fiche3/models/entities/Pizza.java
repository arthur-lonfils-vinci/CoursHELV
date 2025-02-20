package be.vinci.ipl.cae.cae_exercices_fiche3.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pizzas")
@Data
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

}
