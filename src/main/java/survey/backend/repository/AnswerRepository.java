package survey.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import survey.backend.entities.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
