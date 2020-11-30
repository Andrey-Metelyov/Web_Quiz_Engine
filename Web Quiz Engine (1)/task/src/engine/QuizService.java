package engine;

import java.util.List;

public interface QuizService {
    QuizEntity getQuizById(Long id);

    QuizEntity saveQuiz(QuizEntity quizEntity);

    List<QuizEntity> getAllQuizzes();
}
