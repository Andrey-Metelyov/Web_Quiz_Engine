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

    public QuizAnswer(int answer) {
        this.answer = answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "QuizAnswer{" +
                "id=" + id +
                ", answer=" + answer +
                '}';
    }
}
