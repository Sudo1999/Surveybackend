package survey.backend.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PoeDto {
        private int id;
        private String title;
        private LocalDate beginDate;
        private LocalDate endDate;
        private PoeTypeDto poeType;
        @Builder.Default
        private List<StagiaireDto> stagiaires = new ArrayList<>();
}
