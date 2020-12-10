package engine;

import java.util.Set;

public class Answer {
    private Set<Integer> answer;

    public Answer() {
    }

    public Answer(Set<Integer> answer) {
        this.answer = answer;
    }

    public Set<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(Set<Integer> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer=" + answer +
                '}';
    }
}
