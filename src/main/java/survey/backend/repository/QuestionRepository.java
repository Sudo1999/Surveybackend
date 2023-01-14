package survey.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import survey.backend.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
