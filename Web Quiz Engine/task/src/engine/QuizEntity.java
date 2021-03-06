package engine;

import trash.QuizAnswerEntity;

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

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "quiz_id", nullable = false)
    private List<QuizOption> options = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "quiz_id")
    private Set<QuizAnswerEntity> answers = new HashSet<>();

    public Set<Integer> getAnswer() {
        Set<Integer> result = new HashSet<>();
        for (QuizAnswerEntity answer : answers) {
            result.add(answer.getAnswer());
        }
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<QuizOption> getOptions() {
        return options;
    }

//    public void setOptions(List<QuizOption> options) {
//        this.options = options;
//    }

    public void setOptions(String[] options) {
        for (String option : options) {
            this.options.add(new QuizOption(option));
        }
    }

    public Set<QuizAnswerEntity> getAnswers() {
        return answers;
    }

//    public void setAnswers(Set<QuizAnswer> answers) {
//        this.answers = answers;
//    }

    public void setAnswers(int[] answers) {
        for (int answer : answers) {
            this.answers.add(new QuizAnswerEntity(answer));
        }

    }

    //    public void setOptions(List<String> options) {
//        for (String option : options) {
//            this.options.add(new QuizOption(option));
//        }
//    }
//
//    public void setAnswer(Set<Integer> answers) {
//        for (Integer answer : answers) {
//            this.answers.add(new QuizAnswer(answer));
//        }
//    }

    @Override
    public String toString() {
        return "QuizEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                ", answer=" + answers +
                '}';
    }
}
