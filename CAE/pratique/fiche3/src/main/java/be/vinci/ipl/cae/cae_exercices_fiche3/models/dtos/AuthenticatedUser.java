package be.vinci.ipl.cae.cae_exercices_fiche3.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticatedUser {
    private String username;
    private String token;
}