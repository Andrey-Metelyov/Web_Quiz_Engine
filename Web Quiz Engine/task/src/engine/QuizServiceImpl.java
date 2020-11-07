package engine;

import org.springframework.beans.factory.annotation.Autowired;

public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;


    @Override
    public QuizEntity getQuizById(Long id) {
        return quizRepository.findById(id).get();
    }

    @Override
    public QuizEntity saveQuiz(QuizEntity quizEntity) {
        return quizRepository.save(quizEntity);
    }
}
