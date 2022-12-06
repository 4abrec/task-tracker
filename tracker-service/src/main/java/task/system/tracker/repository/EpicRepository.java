package task.system.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.system.tracker.domain.Epic;

@Repository
public interface EpicRepository extends JpaRepository<Epic, String> {
}
