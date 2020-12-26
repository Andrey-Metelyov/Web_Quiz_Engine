package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CompletedServiceImpl implements CompletedService {
    @Autowired
    CompletedRepository completedRepository;

    @Override
    public void save(Completed completed) {
        completedRepository.save(completed);
    }

    @Override
    public Page<Completed> getCompletedByUser(User user, Integer page) {
        System.out.println("Find completed by user: " + user + ", page=" + page);
        System.out.println(completedRepository.findAll().toString());
        return completedRepository.findByUserIdOrderByCompletedAtDesc(user.getId(), PageRequest.of(page, 10));
    }

    @Override
    public void deleteByQuizId(Long id) {
        completedRepository.deleteByQuizId(id);
    }
}
