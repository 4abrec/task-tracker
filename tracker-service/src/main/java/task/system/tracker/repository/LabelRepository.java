package task.system.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.system.tracker.domain.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, String> {
}
