package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private QuizService quizService;

    @Autowired
    private UserService userService;

    @Autowired
    private CompletedService compService;

    public TaskController() {
    }

    @PostMapping(path = "register")
    public ResponseEntity<User> addNewUser(@Valid @RequestBody final User user) {
        System.out.println("Register new user: " + user);
        Optional<User> userInDb = userService.getUserByEmail(user.getEmail());
        if (userInDb.isPresent()) {
            System.out.println("user = " + user + " already exit");
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PostMapping(path = "quizzes")
    public ResponseEntity<QuizEntity> add(@Valid @RequestBody final QuizEntity entity) {
        System.out.println("New quiz: " + entity);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("Current user: " + currentPrincipalName);
        Optional<User> user = userService.getUserByEmail(currentPrincipalName);
        entity.setAuthor(user.get().getId());
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
            User user = getCurrentUser();
            Completed completed = new Completed(user.getId(), quiz.getId(), Instant.now());
            compService.save(completed);
            quizService.saveQuiz(quiz);
            return new AnswerFeedback(true, "Congratulations, you're right!");
        }
        return new AnswerFeedback(false, "Wrong answer! Please, try again.");
    }

    @DeleteMapping(path = "quizzes/{id}")
    public ResponseEntity<Long> deleteQuiz(@PathVariable Long id) {
        System.out.println("Delete quiz : " + id);
        Optional<QuizEntity> entity = quizService.getQuizById(id);
        if (!entity.isPresent()) {
            System.out.println("Quiz with id " + id + " not found");
            return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
        }
        System.out.println("Deleting entity: " + entity.get());
        User user = getCurrentUser();
        if (entity.get().getAuthor() == user.getId()) {
            System.out.println("Deleting quiz");
            quizService.deleteById(id);
            System.out.println("Deleting answers");
            compService.deleteByQuizId(id);
            return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        }
        System.out.println("Wrong user");
        return new ResponseEntity<>(id, HttpStatus.FORBIDDEN);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("Current user: " + currentPrincipalName);
        Optional<User> user = userService.getUserByEmail(currentPrincipalName);
        System.out.println("Current user object: " + user.get());
        return user.get();
    }

//    @GetMapping(path = "quizzes")
//    public Collection<QuizEntity> getAllQuzzes() {
//        System.out.println("Get all quizzes");
//        return quizService.getAllQuizzes();
//    }

    @GetMapping(path = "quizzes")
    public Page<QuizEntity> getAllQuzzes(@RequestParam Optional<Integer> page) {
        System.out.println("Get all quizzes");
        return quizService.getAllQuizzes(page.orElse(0));
    }

    @GetMapping(path = "quizzes/completed")
    public Page<Completed> getCompletedQuzzes(@RequestParam Optional<Integer> page) {
        System.out.println("Get completed quizzes");
        return compService.getCompletedByUser(getCurrentUser(), page.orElse(0));
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
