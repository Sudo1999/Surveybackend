package survey.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import survey.backend.entities.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

}
