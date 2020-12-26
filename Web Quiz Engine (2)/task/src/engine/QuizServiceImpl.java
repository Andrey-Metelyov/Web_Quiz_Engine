package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Optional<QuizEntity> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    @Override
    public QuizEntity saveQuiz(QuizEntity quizEntity) {
        return quizRepository.save(quizEntity);
    }

    @Override
    public void deleteById(Long id) {
        quizRepository.deleteById(id);
    }

    @Override
    public List<QuizEntity> getAllQuizzes() {
        return (List<QuizEntity>) quizRepository.findAll();
    }
}
