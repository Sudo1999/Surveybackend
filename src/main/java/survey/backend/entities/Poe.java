package survey.backend.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter @Setter
public class Poe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 150, nullable = false)
    private String title;
    @Column(name = "begin_date", nullable = false)
    private Date beginDate;
    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private PoeType poeType;

    @Column(name = "id_aelion", nullable = true)
    private String idAelion;

    @OneToMany
    @JoinColumn(name = "poe_id")    // poe_id sera la clé étrangère de l'entité cible
    private Set<Stagiaire> stagiaires;
}
