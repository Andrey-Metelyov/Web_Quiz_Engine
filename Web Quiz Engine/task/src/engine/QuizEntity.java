package engine;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "quiz")
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String text;

    @OneToMany
    @JoinColumn(name = "quiz_id", nullable = false)
    private List<QuizOption> options = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "quiz_id")
    private Set<QuizAnswer> answer = new HashSet<>();
}
