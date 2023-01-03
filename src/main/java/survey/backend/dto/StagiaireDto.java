package survey.backend.dto;

import lombok.*;
//import survey.backend.entities.Stagiaire;
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

    /**
     ^ = Début de chaîne - $ = Fin de chaîne
     [...] = ensemble de caractères autorisés (ou interdits avec un ^ [^...])
     [a-z] = intervalle de lettres autorisées (ou de chiffres avec [0-9])
     [0-9]{3} = suite de trois chiffres
     [0-9]{4,6} = suite de 4 chiffres au minimum à 6 chiffres au maximum
     \ ou \\ => le backslash suivi d'un des caractères ^ $ \ | { } [ ] ( ) ? ! + * # indique le caractère en tant que tel
     ? => caractère facultatif de l'élément qui précède
     */
    @NotBlank
    @Pattern(regexp = "^(0|\\+33)[1-9][0-9]{8}$")
    private String phoneNumber;

    @NotNull
    @Past
    private Date birthDate;

//    public Stagiaire toStagiaire() {
//        Stagiaire stagiaire = new Stagiaire();
//        stagiaire.setId(this.id);
//        stagiaire.setLastName(this.lastName);
//        stagiaire.setFirstName(this.firstName);
//        stagiaire.setEmail(this.email);
//        stagiaire.setPhoneNumber(this.phoneNumber);
//        stagiaire.setBirthDate(this.birthDate);
//        return stagiaire;
//    }
}
