package engine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

public interface CompletedRepository extends PagingAndSortingRepository<Completed, Long> {
    Page<Completed> findByUserIdOrderByCompletedAtDesc(int userId, Pageable pageable);

    @Transactional
    void deleteByQuizId(Long quizId);
}
