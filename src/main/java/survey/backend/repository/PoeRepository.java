package survey.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import survey.backend.entities.Poe;
import java.util.Date;
import java.util.List;

public interface PoeRepository extends CrudRepository<Poe, Long> {

    @Query("select p from Poe p where p.endDate between :firstDate and :lastDate")
    List<Poe> findByEndingInRange(Date firstDate, Date lastDate);

    // Fonction équivalente fournie par Spring Boot
    //List<Poe> findByEndDateBetween(Date firstDate, Date lastDate);
}
