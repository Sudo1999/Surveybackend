package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.AnswerDto;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.AnswerService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping
    public List<AnswerDto> findAll() {
        return answerService.findAll();
    }

    @GetMapping("{answerId}")
    public AnswerDto findById(@PathVariable("answerId") long answerId) {
        return answerService.findById(answerId)
                .orElseThrow(() -> {
                    throw NoDataFoundError.withId("Answer", answerId);
                });
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnswerDto add(@Valid @RequestBody AnswerDto answerDto) {
        return answerService.add(answerDto);
    }

    @DeleteMapping("{answerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("answerId") long answerId) {
        if (!answerService.delete(answerId)) {
            throw NoDataFoundError.withId("Answer", answerId);
        }
    }
}
