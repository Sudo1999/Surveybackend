package survey.backend.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.dto.SurveyDto;
import survey.backend.dto.SurveyFullDto;
import survey.backend.entities.Question;
import survey.backend.entities.Survey;
import survey.backend.repository.QuestionRepository;
import survey.backend.repository.SurveyRepository;
import survey.backend.utils.StreamUtils;
import java.util.*;

@Service
public class SurveyService implements survey.backend.service.SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SurveyDto> findAll() {
        return this.surveyRepository.findAll()
                .stream()
                .map(surveyEntity -> modelMapper.map(surveyEntity, SurveyDto.class))
                .toList();
    }

    @Override
    public SurveyDto add(SurveyDto surveyDto) {
        Survey surveyEntity = modelMapper.map(surveyDto, Survey.class);
        this.surveyRepository.save(surveyEntity);
        return modelMapper.map(surveyEntity, SurveyDto.class);
    }

    @Override
    public Optional<SurveyDto> update(SurveyDto surveyDto) {
        return this.surveyRepository.findById(surveyDto.getId())
                .map(surveyEntity -> {
                    // Update entity object from db with dto fields
                    modelMapper.map(surveyDto, surveyEntity);     // C'est bien surveyEntity et non Survey.class
                    // Synchronize with database
                    surveyRepository.save(surveyEntity);
                    // Transform entity updated in dto
                    return modelMapper.map(surveyEntity, SurveyDto.class);
                });
    }

    @Override
    public boolean delete(long id) {
        Optional<Survey> optSurvey = this.surveyRepository.findById(id);
        if (optSurvey.isPresent()) {
            this.surveyRepository.delete(optSurvey.get());
            return true;
        }
        return false;
    }

    // Fonctions concernant la classe SurveyFullDto contenant l'attribut Set<QuestionDto> questions

    @Override
    public Optional<SurveyFullDto> findById(long id) {
        return this.surveyRepository.findById(id)
                .map(surveyEntity -> modelMapper.map(surveyEntity, SurveyFullDto.class));
    }

    @Override
    public Optional<SurveyFullDto> addQuestion(long surveyId, long questionId) {
        return surveyRepository.findById(surveyId)
                .flatMap(surveyEntity -> questionRepository.findById(questionId)
                        .map(questionEntity -> {
                            surveyEntity.getQuestions().add(questionEntity);
                            surveyRepository.save(surveyEntity);
                            return modelMapper.map(surveyEntity, SurveyFullDto.class);
                        })
                );
    }

    @Override
    public Optional<SurveyFullDto> addQuestions(long surveyId, Collection<Long> questionsIds) {
        return surveyRepository.findById(surveyId)
                .flatMap(surveyEntity -> {
                    var questionEntities = StreamUtils.toStream(questionRepository.findAllById(questionsIds))
                            .toList();
                    if (questionsIds.size() != questionEntities.size()) {
                        return Optional.empty();
                    }
                    surveyEntity.getQuestions().addAll(questionEntities);
                    surveyRepository.save(surveyEntity);
                    return Optional.of(modelMapper.map(surveyEntity, SurveyFullDto.class));
                });
    }

    @Override
    public Optional<SurveyFullDto> removeQuestion(long surveyId, long questionId) {

        var optSurvey = this.surveyRepository.findById(surveyId);
        var optQuestion = this.questionRepository.findById(questionId);

        if (optSurvey.isPresent() && optQuestion.isPresent()) {
            var surveyEntity = optSurvey.get();
            var questionEntity = optQuestion.get();

            Set<Question> updatedQuestions = surveyEntity.getQuestions();
            updatedQuestions.remove(questionEntity);
            surveyEntity.setQuestions(updatedQuestions);
            this.surveyRepository.save(surveyEntity);
            return Optional.of(modelMapper.map(surveyEntity, SurveyFullDto.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<SurveyFullDto> removeAllQuestions(long surveyId) {
        var optSurvey = this.surveyRepository.findById(surveyId);
        if(optSurvey.isPresent()) {
            var surveyEntity = optSurvey.get();
            Set<Question> set =  new HashSet<Question>();
            surveyEntity.setQuestions(set);
            this.surveyRepository.save(surveyEntity);
            return Optional.of(modelMapper.map(surveyEntity, SurveyFullDto.class));
        }
        return Optional.empty();
    }
}
