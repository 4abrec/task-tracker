package task.system.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.system.tracker.domain.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, String> {
}
