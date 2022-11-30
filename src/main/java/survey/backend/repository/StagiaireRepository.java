package survey.backend.repository;

import org.springframework.data.repository.CrudRepository;
import survey.backend.entities.Stagiaire;

public interface StagiaireRepository extends CrudRepository<Stagiaire, Long> {
    // C'est elle qui va relier les entités à la table de la base de données
}