package survey.backend.entities;

import lombok.Getter;
import lombok.Setter;
import survey.backend.enums.AnswerType;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String wording;

    @Enumerated(EnumType.STRING)
    @Column(length = 12, nullable = false)
    private AnswerType answerType;

    @OneToMany
    @JoinColumn(name = "question_id")    // question_id sera la clé étrangère de l'entité cible Answer
    private List<Answer> answers = new ArrayList<Answer>();
}
