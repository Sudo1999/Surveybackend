package survey.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import survey.backend.enums.AnswerType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class QuestionDto {

    private Long id;

    @NotBlank
    private String wording;
    @NotNull
    private AnswerType answerType;
}
