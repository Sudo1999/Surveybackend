package survey.backend.dto;

import lombok.*;
import survey.backend.entities.Stagiaire;
import javax.validation.constraints.*;
import java.util.Date;

@Builder    // Le Builder permet de faire des constructeurs partiels (avec le nombre de paramètres que l'on veut)
@NoArgsConstructor  // Toutes ces annotations Lombok sont utilisées avant la compilation
@AllArgsConstructor
@Getter @Setter
public class StagiaireDto {

    private Long id;
    @NotBlank       // include @NotNull
    private String lastName;
    @NotBlank
    private String firstName;
    @NotNull
    @Email
    private String email;
    //@Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
    private String phoneNumber;
    @Past
    private Date birthDate;

    public Stagiaire toStagiaire() {
        Stagiaire stagiaire = new Stagiaire();
        stagiaire.setId(this.id);
        stagiaire.setLastName(this.lastName);
        stagiaire.setFirstName(this.firstName);
        stagiaire.setEmail(this.email);
        stagiaire.setPhoneNumber(this.phoneNumber);
        stagiaire.setBirthDate(this.birthDate);
        return stagiaire;
    }
}
