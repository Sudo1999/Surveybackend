package survey.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import survey.backend.enums.PoeType;
//import survey.backend.entities.Poe;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PoeDto {

        private Long id;

        @NotBlank
        private String title;
        @NotNull
        private Date beginDate;
        @NotNull
        private Date endDate;
        @NotNull
        private PoeType poeType;

        private String idAelion;

//        public Poe toPoe() {
//                Poe poe = new Poe();
//                poe.setId(this.id);
//                poe.setTitle(this.title);
//                poe.setBeginDate(this.beginDate);
//                poe.setEndDate(this.endDate);
//                poe.setPoeType(this.poeType);
//                poe.setIdAelion(this.idAelion);
//                return poe;
//        }
}
