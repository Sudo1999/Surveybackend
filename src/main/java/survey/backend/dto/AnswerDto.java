package survey.backend.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;

public class Answer {

    @Builder        // Le Builder permet de faire des constructeurs partiels (avec le nombre de paramètres que l'on veut)
    @NoArgsConstructor      // Toutes ces annotations Lombok sont utilisées avant la compilation
    @AllArgsConstructor
    @Getter @Setter
    public class AnswerDto {

        private Long id;

        @NotBlank
        private String text;
    }
}
