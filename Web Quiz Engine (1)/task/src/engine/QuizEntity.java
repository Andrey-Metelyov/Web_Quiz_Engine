package engine;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "quiz_id", nullable = false)
//    private List<QuizOption> options = new ArrayList<>();
    @ElementCollection
//    @CollectionTable(name = "quiz_options", joinColumns = @JoinColumn(name = "quiz_id"))
//    @Column(name = "options")
    @OrderColumn
    private List<String> options = new ArrayList<>();

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "quiz_id")
    @JsonIgnore
    @ElementCollection
//    @CollectionTable(name = "quiz_answer", joinColumns = @JoinColumn(name = "quiz_id"))
//    @Column(name = "answer")
    private Set<Integer> answer = new HashSet<>();

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

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public Set<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(Set<Integer> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuizEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                ", answer=" + answer +
                '}';
    }
}
