package survey.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import survey.backend.enums.PoeType;
import survey.backend.enums.SurveyType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class SurveyDto {

    private Long id;

    @NotBlank
    private String title;
    @NotNull
    private SurveyType surveyType;
    @NotNull
    private PoeType poeType;
}
