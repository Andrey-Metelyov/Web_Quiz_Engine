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

    public QuizOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "QuizOption{" +
                "id=" + id +
                ", option='" + option + '\'' +
                '}';
    }
}