package be.vinci.ipl.cae.demo.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * NewPizza DTO.
 */
@Data
@NoArgsConstructor
public class NewPizza {

  private String title;
  private String content;
}
