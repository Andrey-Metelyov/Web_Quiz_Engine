package engine;

import org.springframework.data.domain.Page;

public interface CompletedService {

    void save(Completed completed);

    Page<Completed> getCompletedByUser(User user, Integer page);

    void deleteByQuizId(Long id);
}
