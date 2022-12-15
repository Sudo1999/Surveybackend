package survey.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import survey.backend.entities.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName); // On peut faire un findBy avec tous les attributs souhaités, mais un par un
    // Pour plusieurs à la fois on fait un @Query avec des @Param (voir le StagiaireRepository)
}
