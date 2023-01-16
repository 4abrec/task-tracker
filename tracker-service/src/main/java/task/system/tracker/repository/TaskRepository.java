package task.system.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import task.system.tracker.domain.Task;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

    @Query("SELECT t FROM Task t JOIN History h ON t.history.id = h.id " +
            "JOIN Epic e ON h.epic.id = e.id WHERE e.project.id = ?1 AND t.createdAt > ?2")
    List<Task> findByProjectIdAndCreatedAt(String projectId, LocalDateTime date);
}
