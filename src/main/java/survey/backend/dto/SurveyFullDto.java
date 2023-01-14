package survey.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.Set;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class SurveyFullDto extends SurveyDto {

    private Set<QuestionDto> questions;
}
