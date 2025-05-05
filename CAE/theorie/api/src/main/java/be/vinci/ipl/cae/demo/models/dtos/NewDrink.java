package be.vinci.ipl.cae.demo.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * NewDrink DTO.
 */
@Data
@NoArgsConstructor
public class NewDrink {

  private String title;
  private String image;
  private double volume;
  private double price;
}
