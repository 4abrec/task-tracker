package task.system.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.system.tracker.domain.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, String> {
}
