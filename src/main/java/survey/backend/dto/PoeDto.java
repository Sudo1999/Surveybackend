package survey.backend.dto;

import lombok.*;
import survey.backend.entities.Poe;
import survey.backend.entities.PoeType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PoeDto {
        private Long id;
        @NotBlank
        private String title;
        @NotNull
        private Date beginDate;
        @NotNull
        private Date endDate;
        @NotEmpty
        private PoeType poeType;
        private String idAelion;

//        @Builder.Default
//        private Set<StagiaireDto> stagiaires = null;

        public Poe toPoe() {
                Poe poe = new Poe();
                poe.setId(this.id);
                poe.setTitle(this.title);
                poe.setBeginDate(this.beginDate);
                poe.setEndDate(this.endDate);
                poe.setPoeType(this.poeType);
                poe.setIdAelion(this.idAelion);
                return poe;
        }
}
