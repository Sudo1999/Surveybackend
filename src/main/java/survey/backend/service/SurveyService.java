package survey.backend.service;

import survey.backend.dto.SurveyDto;
import survey.backend.dto.SurveyFullDto;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SurveyService {

    List<SurveyDto> findAll();

    SurveyDto add(SurveyDto surveyDto);

    Optional<SurveyDto> update(SurveyDto surveyDto);

    boolean delete(long id);

    // Fonctions concernant la classe SurveyFullDto contenant l'attribut Set<QuestionDto> questions

    Optional<SurveyFullDto> findById(long id);

    Optional<SurveyFullDto> addQuestion(long surveyId, long questionId);

    Optional<SurveyFullDto> addQuestions(long surveyId, Collection<Long> questionsIds);

    Optional<SurveyFullDto> removeQuestion(long surveyId, long questionId);

    Optional<SurveyFullDto> removeAllQuestions(long surveyId);
}
