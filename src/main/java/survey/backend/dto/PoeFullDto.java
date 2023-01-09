package survey.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.Set;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PoeFullDto extends PoeDto {

    private Set<StagiaireDto> stagiaires;
}
