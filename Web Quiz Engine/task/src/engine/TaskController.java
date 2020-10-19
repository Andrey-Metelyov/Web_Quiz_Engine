package engine;

import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {
    public TaskController() {
    }

    @PostMapping(path = "/api/quiz"/*, consumes = "application/json"*/)
    public Answer getAnswer(@RequestParam("answer") int answer) {
        if (answer == 2) {
            return new Answer(true, "Congratulations, you're right!");
        }
        return new Answer(false, "Wrong answer! Please, try again.");
    }

    @GetMapping(path = "/api/quiz")
    public Quiz getTask(){
        return new Quiz("The Java Logo",
                "What is depicted on the Java logo?",
                new String[] {"Robot","Tea leaf","Cup of coffee","Bug"});
    }
}
