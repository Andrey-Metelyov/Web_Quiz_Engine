package engine;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
    public Quiz addQuiz(
            @RequestBody Quiz quiz) {
        quiz.setId(quizMap.size());
        quizMap.put(quiz.getId(), quiz);
        return quiz;
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        return new ResponseEntity<>(quiz, headers, HttpStatus.OK);
//        return quiz;
    }

    @PostMapping(path = "/api/quizzes/{id}/solve")
    public Answer getAnswer(@PathVariable int id, @RequestParam("answer") int answer) {
        Quiz quiz = quizMap.get(id);
        System.out.println(quiz);
        System.out.println("answer = " + answer);
        if (answer == quizMap.get(id).getAnswer()) {
            return new Answer(true, "Congratulations, you're right!");
        }
        return new Answer(false, "Wrong answer! Please, try again.");
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
