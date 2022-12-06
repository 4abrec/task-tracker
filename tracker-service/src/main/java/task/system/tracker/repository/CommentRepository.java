package task.system.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.system.tracker.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
}
