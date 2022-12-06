package task.system.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.system.tracker.domain.Bug;

@Repository
public interface BugRepository extends JpaRepository<Bug, String> {
}
