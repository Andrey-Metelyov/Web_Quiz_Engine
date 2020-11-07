package engine;

import javax.persistence.*;

@Entity
public class QuizOption {
    @Id
    @Column(name = "option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String option;
}
