package engine;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    Optional<QuizEntity> getQuizById(Long id);

    QuizEntity saveQuiz(QuizEntity quizEntity);

    void deleteById(Long id);

    List<QuizEntity> getAllQuizzes();

    Page<QuizEntity> getAllQuizzes(Integer page);
}
