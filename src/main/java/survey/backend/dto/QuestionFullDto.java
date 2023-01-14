package survey.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class QuestionFullDto extends QuestionDto {

    private List<AnswerDto> answers;
}
