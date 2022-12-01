package survey.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import survey.backend.entities.Stagiaire;

public interface StagiaireRepository extends CrudRepository<Stagiaire, Long> {
    // C'est elle qui va relier les entités à la table de la base de données

    @Query(
            value = "SELECT id, last_name, first_name, birth_date, email, phone_number FROM stagiaire" +
                    " WHERE last_name = :lastName AND first_name = :firstName",
            nativeQuery = true
    )
    public Iterable<Stagiaire> listByLastNameAndFirstName(@Param(value = "lastName") String lastName,
                                                          @Param(value = "firstName") String firstName);

    public Iterable<Stagiaire> findByLastName(String lastName);
    public Iterable<Stagiaire> findByFirstName(String firstName);
}