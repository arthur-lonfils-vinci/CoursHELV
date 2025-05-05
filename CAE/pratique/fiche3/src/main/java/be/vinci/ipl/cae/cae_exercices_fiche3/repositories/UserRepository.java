package be.vinci.ipl.cae.cae_exercices_fiche3.repositories;

import be.vinci.ipl.cae.cae_exercices_fiche3.models.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
