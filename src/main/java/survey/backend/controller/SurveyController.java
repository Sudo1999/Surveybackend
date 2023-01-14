package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.SurveyDto;
import survey.backend.dto.SurveyFullDto;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.implement.SurveyService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping
    public List<SurveyDto> findAll() {
        return surveyService.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public SurveyDto add(@Valid @RequestBody SurveyDto surveyDto) {
        return surveyService.add(surveyDto);
    }

    @DeleteMapping("{surveyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("surveyId") long surveyId) {
        if (!surveyService.delete(surveyId)) {
            throw NoDataFoundError.withId("Survey", surveyId);
        }
    }

    // Fonctions prenant en compte la liste des questions du Questionnaire :

    @GetMapping("{surveyId}")
    public SurveyFullDto findById(@PathVariable("surveyId") long surveyId) {
        return surveyService.findById(surveyId)
                .orElseThrow(() -> {
                    throw NoDataFoundError.withId("Survey", surveyId);
                });
    }

    @PatchMapping("{surveyId}/addQuestion/{questionId}")
    public SurveyFullDto addQuestion(
            @PathVariable("surveyId") long surveyId,
            @PathVariable("questionId") long questionId
    ) {
        return surveyService.addQuestion(surveyId, questionId)
                .orElseThrow(() -> {
                    throw NoDataFoundError.withIds("Either Survey or question", surveyId, questionId);
                });
    }

    @PatchMapping("{surveyId}/addQuestions")
    public SurveyFullDto addQuestions(
            @PathVariable("surveyId") long surveyId,
            @RequestBody List<Long> questionsIds
    ) {
        return surveyService.addQuestions(surveyId, questionsIds)
                .orElseThrow(() -> {
                    throw NoDataFoundError.withId("Either Survey or questions in Survey", surveyId);
                });
    }

    @DeleteMapping({"{surveyId}/removeQuestion/{questionId}"})
    public SurveyFullDto removeQuestion(
            @PathVariable("surveyId") long surveyId,
            @PathVariable("questionId") long questionId
    ) {
        var optSurveyFullDto = this.surveyService.removeQuestion(surveyId, questionId);
        if(optSurveyFullDto.isEmpty()){
            throw NoDataFoundError.withValues("Either Survey or question",
                    "Survey Id = " + surveyId + " and question Id = " + questionId );
        }
        return optSurveyFullDto.get();
    }

    @DeleteMapping("{surveyId}/removeAllQuestions")
    public SurveyFullDto removeAllQuestions(@PathVariable("surveyId") long surveyId) {
        var optSurveyFullDto = this.surveyService.removeAllQuestions(surveyId);
        if(optSurveyFullDto.isEmpty()){
            throw NoDataFoundError.withId("Either Survey or questions in Survey", surveyId);
        }
        return optSurveyFullDto.get();
    }
}
