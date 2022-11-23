package survey.backend.dto;

import lombok.*;
import java.time.LocalDate;

@Builder    // Le Builder permet de faire des constructeurs avec le nombre de paramètres que l'on veut
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class StagiaireDto {

    private Integer id;     // int n'est pas nullable mais Integer si
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
}