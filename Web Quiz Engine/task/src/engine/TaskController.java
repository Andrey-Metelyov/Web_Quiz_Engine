package engine;

import org.springframework.http.HttpStatus;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class TaskController {
    Map<Integer, Quiz> quizMap = new HashMap<>();

    public TaskController() {
//        quizMap.put(1,
//                new Quiz(1,
//                        "The Java Logo",
//                        "What is depicted on the Java logo?",
//                        new String[]{"Robot", "Tea leaf", "Cup of coffee", "Bug"},
//                        2));
//        quizMap.put(2,
//                new Quiz(2,
//                        "The Zopa Logo",
//                        "What is depicted on the Java logo?",
//                        new String[]{"Robot", "Ass", "Cup of coffee", "Bug"},
//                        1));
    }

    @PostMapping(path = "/api/quizzes")
    @ResponseStatus(HttpStatus.OK)
    public Quiz addQuiz(@Valid @RequestBody Quiz quiz) {
        System.out.println("New quiz to add:" + quiz);
        quiz.setId(quizMap.size());
        quizMap.put(quiz.getId(), quiz);
        System.out.println("New quiz added:" + quiz);
        return quiz;
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        return new ResponseEntity<>(quiz, headers, HttpStatus.OK);
//        return quiz;
    }

    @PostMapping(path = "/api/quizzes/{id}/solve")
    public AnswerFeedback getAnswer(@PathVariable int id, @RequestBody Answer answer) {
        Quiz quiz = quizMap.get(id);
        System.out.println(quiz);
        System.out.println("answer = " + answer);
        Set<Integer> gotAnswer = new HashSet(answer.getAnswer());
        Set<Integer> correctAnswer = quizMap.get(id).getAnswer();
        System.out.println("gotAnswer = " + gotAnswer);
        System.out.println("correctAnswer = " + correctAnswer);
        if (gotAnswer.equals(correctAnswer)) {
            return new AnswerFeedback(true, "Congratulations, you're right!");
        }
        return new AnswerFeedback(false, "Wrong answer! Please, try again.");
    }

    @GetMapping(path = "/api/quizzes")
    public Collection<Quiz> getQuizzes(){
        return quizMap.values();
    }

    @GetMapping(path = "/api/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id){
        System.out.println("get quiz with id: " + id);
        if (!quizMap.containsKey(id)) {
            System.out.println(id + " not found");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        return quizMap.get(id);
    }
}
