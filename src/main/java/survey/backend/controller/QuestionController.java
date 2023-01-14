package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.QuestionDto;
import survey.backend.dto.QuestionFullDto;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.implement.QuestionService;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public Collection<QuestionDto> findAll() {
        return questionService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionDto add(@Valid @RequestBody QuestionDto questionDto) {
        return questionService.add(questionDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        if (!questionService.delete(id)) {
            throw NoDataFoundError.withId("Question", id);
        }
    }

// Fonctions prenant en compte la liste des réponses de la Question :

    @GetMapping("{id}")
    public QuestionFullDto findById(@PathVariable("id") long id) {
        Optional<QuestionFullDto> optQuestionFullDto = questionService.findById(id);
        if (optQuestionFullDto.isPresent()) {
            return optQuestionFullDto.get();
        } else {
            throw NoDataFoundError.withId("Question", id);
        }
    }

    @PatchMapping("{questionId}/addAnswer/{answerId}")
    public QuestionFullDto addAnswer(
            @PathVariable("questionId") long questionId,
            @PathVariable("answerId") long answerId
    ) {
        return questionService.addAnswer(questionId, answerId)
                .orElseThrow(() -> {
                    throw NoDataFoundError.withIds("Either Question or Answer", questionId, answerId);
                });
    }

    @PatchMapping("{questionId}/addAnswers")
    public QuestionFullDto addAnswers(
            @PathVariable("questionId") long questionId,
            @RequestBody List<Long> answersIds
    ){
        return questionService.addAnswers(questionId, answersIds)
                .orElseThrow(() -> {
                    throw NoDataFoundError.withId("Either Question or Answers in Question", questionId);
                });
    }

    @DeleteMapping("{questionId}/removeAnswer/{answerId}")
    public QuestionFullDto removeAnswer(
            @PathVariable("questionId") long questionId,
            @PathVariable("answerId") long answerId
    ) {
        var optQuestionFullDto = this.questionService.removeAnswer(questionId, answerId);
        if(optQuestionFullDto.isEmpty()){
            throw NoDataFoundError.withValues("Either Question or Answer",
                    "Question Id = " + questionId + " and answer Id = " + answerId );
        }
        return optQuestionFullDto.get();
    }
}
