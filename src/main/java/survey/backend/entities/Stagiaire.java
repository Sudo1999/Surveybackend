package survey.backend.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="stagiaire")
@Getter @Setter
public class Stagiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // IDENTITY = entier non nul auto-incrémenté
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="last_name", nullable = false)
    private String lastName;
    @Column(name="first_name", nullable = false)
    private String firstName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(name="phone_number", nullable = false)
    private String phoneNumber;
    @Column(name="birth_date", nullable = false)
    private Date birthDate;
}