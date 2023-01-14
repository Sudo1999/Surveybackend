package survey.backend.service;

import survey.backend.dto.QuestionDto;
import survey.backend.dto.QuestionFullDto;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface QuestionService {

    List<QuestionDto> findAll();

    QuestionDto add(QuestionDto questionDto);

    boolean delete(long id);

    // Fonctions concernant la classe QuestionFullDto contenant l'attribut List<AnswerDto> answers

    Optional<QuestionFullDto> findById(long Id);

    Optional<QuestionFullDto> addAnswer(long questionId, long answerId);

    Optional<QuestionFullDto> addAnswers(long questionId, Collection<Long> answersIds);

    Optional<QuestionFullDto> removeAnswer(long questionId, long answerId);

    Optional<QuestionFullDto> removeAllAnswers(long questionId);

}
