package survey.backend.entities;

import lombok.Getter;
import lombok.Setter;
import survey.backend.enums.SurveyType;
import survey.backend.enums.PoeType;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private SurveyType surveyType;

    @Enumerated(EnumType.STRING)
    @Column(length = 4, nullable = false)
    private PoeType poeType;

    //@Transient      // Pour ignorer momentanément l'association pendant le développement
    @ManyToMany
    @JoinTable(name = "contains",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private Set<Question> questions = new HashSet<>();
}
