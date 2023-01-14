package survey.backend.service;

import survey.backend.dto.AnswerDto;
import java.util.List;
import java.util.Optional;

public interface AnswerService {

    List<AnswerDto> findAll();

    Optional<AnswerDto> findById(long id);

    AnswerDto add(AnswerDto answerDto);

    Optional<AnswerDto> update(AnswerDto answerDto);

    boolean delete(long id);

}
