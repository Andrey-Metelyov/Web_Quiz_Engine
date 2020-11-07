package engine;

import javax.persistence.*;

@Entity
public class QuizAnswer {
    @Id
    @Column(name = "answer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int answer;
}
