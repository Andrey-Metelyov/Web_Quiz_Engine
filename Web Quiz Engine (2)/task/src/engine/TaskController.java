package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private QuizService quizService;

    @Autowired
    private UserService userService;

    public TaskController() {
    }

    @PostMapping(path = "register")
    public ResponseEntity<User> addNewUser(@Valid @RequestBody final User user) {
        System.out.println("Register new user: " + user);
        Optional<User> userInDb = userService.getUserByEmail(user.getEmail());
        if (userInDb.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(path = "quizzes")
    public ResponseEntity<QuizEntity> add(@Valid @RequestBody final QuizEntity entity) {
        System.out.println("New quiz: " + entity);
        QuizEntity savedEntity = quizService.saveQuiz(entity);
        return new ResponseEntity<>(savedEntity, HttpStatus.OK);
    }

    @PostMapping(path = "quizzes/{id}/solve")
    public AnswerFeedback getAnswer(@PathVariable Long id, @RequestBody Answer answer) {
        QuizEntity quiz = quizService.getQuizById(id).get();
        System.out.println(quiz);
        System.out.println("answer = " + answer);
        Set<Integer> gotAnswer = answer.getAnswer();
        Set<Integer> correctAnswer = quiz.getAnswer();
        System.out.println("gotAnswer = " + gotAnswer);
        System.out.println("correctAnswer = " + correctAnswer);
        if (gotAnswer.equals(correctAnswer)) {
            return new AnswerFeedback(true, "Congratulations, you're right!");
        }
        return new AnswerFeedback(false, "Wrong answer! Please, try again.");
    }

    @GetMapping(path = "quizzes")
    public Collection<QuizEntity> getAllQuzzes() {
        System.out.println("Get all quizzes");
        return quizService.getAllQuizzes();
    }

    @GetMapping(path = "quizzes/{id}")
    public ResponseEntity<QuizEntity> getQuiz(@PathVariable final Long id) {
        System.out.println("Get quiz with id: " + id);
        Optional<QuizEntity> quiz = quizService.getQuizById(id);
        if (quiz.isPresent()) {
            return new ResponseEntity<>(quiz.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}
