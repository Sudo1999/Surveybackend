package survey.backend.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.dto.QuestionDto;
import survey.backend.dto.QuestionFullDto;
import survey.backend.entities.Answer;
import survey.backend.entities.Question;
import survey.backend.repository.AnswerRepository;
import survey.backend.repository.QuestionRepository;
import survey.backend.utils.StreamUtils;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService implements survey.backend.service.QuestionService {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<QuestionDto> findAll() {
        return this.questionRepository.findAll()
                .stream().map(questionEntity -> modelMapper.map(questionEntity, QuestionDto.class))
                .toList();
    }

    @Override
    public QuestionDto add(QuestionDto questionDto) {
        Question questionEntity = modelMapper.map(questionDto, Question.class);
        this.questionRepository.save(questionEntity);
        return modelMapper.map(questionEntity, QuestionDto.class);
    }

    @Override
    public boolean delete(long id) {
        Optional<Question> optQuestion = this.questionRepository.findById(id);
        if(optQuestion.isPresent()) {
            this.questionRepository.delete(optQuestion.get());
            return true;
        }
        return false;
    }

    // Fonctions concernant la classe QuestionFullDto contenant l'attribut List<AnswerDto> answers

    @Override
    public Optional<QuestionFullDto> findById(long id) {
        return this.questionRepository.findById(id)
                .map(questionEntity -> modelMapper.map(questionEntity, QuestionFullDto.class));
    }

    @Override
    public Optional<QuestionFullDto> addAnswer(long questionId, long answerId) {
        return questionRepository.findById(questionId)
                .flatMap(questionEntity -> answerRepository.findById(answerId)
                        .map(answerEntity -> {
                            // Add answer to question
                            questionEntity.getAnswers().add(answerEntity);
                            // Synchronize with database
                            questionRepository.save(questionEntity);
                            // Return updated question as QuestionFullDto
                            return modelMapper.map(questionEntity, QuestionFullDto.class);
                        })
                );
    }

    @Override
    public Optional<QuestionFullDto> addAnswers(long questionId, Collection<Long> answersIds) {
        return questionRepository.findById(questionId)
                .flatMap(questionEntity -> {
                    var answerEntities = StreamUtils.toStream(answerRepository.findAllById(answersIds))
                            .toList();
                    if (answersIds.size() != answerEntities.size()) {
                        return Optional.empty();
                    }
                    // Add answers to question
                    questionEntity.getAnswers().addAll(answerEntities);
                    // Synchronize with database
                    questionRepository.save(questionEntity);
                    // Return updated question as QuestionFullDto
                    return Optional.of(modelMapper.map(questionEntity, QuestionFullDto.class));
                });
    }

    @Override
    public Optional<QuestionFullDto> removeAnswer(long questionId, long answerId) {
        var optQuestion = this.questionRepository.findById(questionId);
        var optAnswer = this.answerRepository.findById(answerId);

        if (optQuestion.isPresent() && optAnswer.isPresent()) {
            var questionEntity = optQuestion.get();
            var answerEntity = optAnswer.get();

            List<Answer> updatedAnswers = questionEntity.getAnswers();
            updatedAnswers.remove(answerEntity);
            questionEntity.setAnswers(updatedAnswers);
            this.questionRepository.save(questionEntity);
            return Optional.of(modelMapper.map(questionEntity, QuestionFullDto.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<QuestionFullDto> removeAllAnswers(long questionId) {
        // TODO
        return Optional.empty();
    }
}
