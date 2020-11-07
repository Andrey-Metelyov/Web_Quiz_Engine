package engine;

public interface QuizService {
    QuizEntity getQuizById(Long id);

    QuizEntity saveQuiz(QuizEntity quizEntity);
}
