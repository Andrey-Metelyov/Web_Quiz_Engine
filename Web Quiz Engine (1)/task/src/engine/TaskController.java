package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/quizzes")
public class TaskController {
//    Map<Integer, Quiz> quizMap = new HashMap<>();
    @Autowired
    private QuizService quizService;

    public TaskController() {
    }

//    @PostMapping(path = "/api/quizzes")
//    @ResponseStatus(HttpStatus.OK)
//    public Quiz addQuiz(@Valid @RequestBody Quiz quiz) {
//        System.out.println("New quiz to add:" + quiz);
//        quiz.setId(quizMap.size());
//        quizMap.put(quiz.getId(), quiz);
//        System.out.println("New quiz added:" + quiz);
//        return quiz;
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_JSON);
////        return new ResponseEntity<>(quiz, headers, HttpStatus.OK);
////        return quiz;
//    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<QuizEntity> addQuiz(@RequestBody final QuizEntity quiz) {
        System.out.println("New quiz to add:" + quiz);
        QuizEntity savedQuiz = quizService.saveQuiz(quiz);
        return new ResponseEntity<>(savedQuiz, HttpStatus.OK);
    }


//    @PostMapping(path = "/api/quizzes/{id}/solve")
//    public AnswerFeedback getAnswer(@PathVariable int id, @RequestBody Answer answer) {
//        Quiz quiz = quizMap.get(id);
//        System.out.println(quiz);
//        System.out.println("answer = " + answer);
//        Set<Integer> gotAnswer = new HashSet(answer.getAnswer());
//        Set<Integer> correctAnswer = quizMap.get(id).getAnswer();
//        System.out.println("gotAnswer = " + gotAnswer);
//        System.out.println("correctAnswer = " + correctAnswer);
//        if (gotAnswer.equals(correctAnswer)) {
//            return new AnswerFeedback(true, "Congratulations, you're right!");
//        }
//        return new AnswerFeedback(false, "Wrong answer! Please, try again.");
//    }
    @PostMapping(path = "/{id}/solve")
    public AnswerFeedback getAnswer(@PathVariable Long id, @RequestBody Answer answer) {
        QuizEntity quiz = quizService.getQuizById(id);
        System.out.println(quiz);
        System.out.println("answer = " + answer);
        Set<Integer> gotAnswer = new HashSet(answer.getAnswer());
        Set<Integer> correctAnswer = quiz.getAnswer();
        System.out.println("gotAnswer = " + gotAnswer);
        System.out.println("correctAnswer = " + correctAnswer);
        if (gotAnswer.equals(correctAnswer)) {
            return new AnswerFeedback(true, "Congratulations, you're right!");
        }
        return new AnswerFeedback(false, "Wrong answer! Please, try again.");
    }

//    @GetMapping(path = "/api/quizzes")
//    public Collection<Quiz> getQuizzes(){
//        return quizMap.values();
//    }

    @GetMapping()
    public Collection<QuizEntity> getQuizzes(){
        System.out.println("Get all quizzes");
        return quizService.getAllQuizzes();
    }

//    @GetMapping(path = "/api/quizzes/{id}")
//    public Quiz getQuiz(@PathVariable int id){
//        System.out.println("get quiz with id: " + id);
//        if (!quizMap.containsKey(id)) {
//            System.out.println(id + " not found");
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "entity not found"
//            );
//        }
//        return quizMap.get(id);
//    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<QuizEntity> getQuiz(@PathVariable final Long id){
        System.out.println("get quiz with id: " + id);
        QuizEntity quizEntity = quizService.getQuizById(id);
        return new ResponseEntity<>(quizEntity, HttpStatus.OK);
//        if (!quizMap.containsKey(id)) {
//            System.out.println(id + " not found");
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "entity not found"
//            );
//        }
//        return quizMap.get(id);
    }
}
