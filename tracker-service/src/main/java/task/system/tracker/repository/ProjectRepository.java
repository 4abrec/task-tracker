package task.system.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.system.tracker.domain.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
}
