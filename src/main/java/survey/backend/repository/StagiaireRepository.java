package survey.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import survey.backend.entities.Stagiaire;
import java.util.List;
import java.util.Optional;

public interface StagiaireRepository extends CrudRepository<Stagiaire, Long> {
    // C'est elle qui va relier les entités à la table de la base de données

    // Native SQL = Méthode non recommandée (on se réfère à la table) :
    // On peut tout passer en List (ordonnée) ou en Set (pas de doublons) à la place de l'iterable
    @Query(
            value = "SELECT id, last_name, first_name, birth_date, email, phone_number FROM Stagiaire" +
                    " WHERE UPPER(last_name) = UPPER(:lastName) AND UPPER(first_name) = UPPER(:firstName)",
            nativeQuery = true
    )
    public Iterable<Stagiaire> listByLastNameAndFirstName(
            @Param(value = "lastName") String lastName,
            @Param(value = "firstName") String firstName
    );

    public Iterable<Stagiaire> findByLastName(String lastName);
    public Iterable<Stagiaire> findByFirstName(String firstName);

    // JPQL/ HQL = La bonne façon d'opérer (on se réfère à la classe) :
    @Query(
            value = "SELECT s FROM Stagiaire s" +
                    " WHERE UPPER(s.lastName) = UPPER(:lastName) AND UPPER(s.firstName) = UPPER(:firstName)"
    )
    public Iterable<Stagiaire> byLastNameAndFirstName(
            @Param(value="lastName") String lastName,
            @Param(value="firstName") String firstName
    );

    // JPQL auto generated according to Spring Data Jpa vocabulary
    List<Stagiaire> findByLastNameIgnoreCase(String lastName);
    List<Stagiaire> findByFirstNameIgnoreCase(String firstName);
    Optional<Stagiaire> findByEmail(String email);

}
