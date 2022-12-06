package task.system.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.system.tracker.domain.SuperSprint;

@Repository
public interface SuperSprintRepository extends JpaRepository<SuperSprint, String> {
}
