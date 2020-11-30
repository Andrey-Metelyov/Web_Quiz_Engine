package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public class Quiz {
    private int id;
    @NotBlank(message = "title is mandatory")
    private String title;
    @NotBlank(message = "text is mandatory")
    private String text;
    @NotNull(message = "options may not be null")
    @Size(min = 2)
    private String[] options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Integer> answer = Collections.emptySet();

    public Quiz() {
    }

    public Quiz(int id, String title, String text, String[] options, Set<Integer> answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        if (answer != null) {
            this.answer = answer;
        } else {
            this.answer = Collections.emptySet();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
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
        return "Quiz{" + System.lineSeparator() +
                "\tid=" + id + System.lineSeparator() +
                "\ttitle='" + title + '\'' + System.lineSeparator() +
                "\ttext='" + text + '\'' + System.lineSeparator() +
                "\toptions=" + Arrays.toString(options) + System.lineSeparator() +
                "\tanswer=" + answer + System.lineSeparator() +
                '}';
    }
}
