package survey.backend.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Builder    // Le Builder permet de faire des constructeurs partiels (avec le nombre de paramètres que l'on veut)
@NoArgsConstructor  // Toutes ces annotations Lombok sont utilisées avant la compilation
@AllArgsConstructor
@Getter @Setter
public class StagiaireDto {

    private Integer id;     // int n'est pas nullable mais par contre Integer l'est

    @NotBlank       // include @NotNull
    private String lastName;

    @NotBlank
    private String firstName;

    @NotNull
    @Email
    private String email;

    @Pattern(regexp = " ^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
    private String phoneNumber;

    @Past
    private LocalDate birthDate;
}
