package engine;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    Optional<QuizEntity> getQuizById(Long id);

    QuizEntity saveQuiz(QuizEntity quizEntity);

    void deleteById(Long id);

    List<QuizEntity> getAllQuizzes();
}
