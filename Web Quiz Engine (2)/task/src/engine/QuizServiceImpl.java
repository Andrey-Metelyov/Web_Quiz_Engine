package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

//    @Override
//    public Page<QuizEntity> getAllQuizzes(Pageable pageable) {
//        return null;
//    }

    @Override
    public Page<QuizEntity> getAllQuizzes(Integer page) {
        return quizRepository.findAll(PageRequest.of(page, 10));
    }
}
