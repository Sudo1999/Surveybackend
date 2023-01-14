package survey.backend.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.dto.AnswerDto;
import survey.backend.entities.Answer;
import survey.backend.repository.AnswerRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService implements survey.backend.service.AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AnswerDto> findAll(){
        return this.answerRepository.findAll()
                .stream()
                .map(answerEntity -> modelMapper.map(answerEntity, AnswerDto.class))
                .toList();
    }

    @Override
    public Optional<AnswerDto> findById(long id){
        return this.answerRepository.findById(id)
                .map(answerEntity -> modelMapper.map(answerEntity, AnswerDto.class));
    }

    @Override
    public AnswerDto add(AnswerDto answerDto) {
        Answer answerEntity = modelMapper.map(answerDto, Answer.class);
        this.answerRepository.save(answerEntity);
        return modelMapper.map(answerEntity, AnswerDto.class);
    }

    @Override
    public Optional<AnswerDto> update(AnswerDto answerDto) {
        return this.answerRepository.findById(answerDto.getId())
                .map(answerEntity -> {
                    // Update entity object from db with dto fields
                    modelMapper.map(answerDto, answerEntity);     // C'est bien answerEntity et non Answer.class
                    // Synchronize with database
                    answerRepository.save(answerEntity);
                    // Transform entity updated in dto
                    return modelMapper.map(answerEntity, AnswerDto.class);
                });
    }

    @Override
    public boolean delete(long id) {
        Optional<Answer> optAnswer = this.answerRepository.findById(id);
        if (optAnswer.isPresent()) {
            this.answerRepository.delete(optAnswer.get());
            return true;
        }
        return false;
    }
}
