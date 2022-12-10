package survey.backend.dto;

import lombok.*;
import survey.backend.entities.Poe;
import survey.backend.entities.PoeType;
import survey.backend.entities.Stagiaire;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PoeDto {
        private Long id;
        @NotBlank
        private String title;
        private Date beginDate;
        private Date endDate;
        private PoeType poeType;

        @Builder.Default
        private List<StagiaireDto> stagiaires = new ArrayList<>();

        public Poe toPoe() {
                Poe poe = new Poe();
                poe.setId(this.id);
                poe.setTitle(this.title);
                poe.setBeginDate(this.beginDate);
                poe.setEndDate(this.endDate);
                poe.setPoeType(this.poeType);
                return poe;
        }
}
