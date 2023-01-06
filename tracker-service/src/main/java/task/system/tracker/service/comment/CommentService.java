package task.system.tracker.service.comment;

import org.springframework.data.domain.Page;
import task.system.tracker.domain.Comment;
import task.system.tracker.dto.comment.CreateCommentRq;
import task.system.tracker.dto.comment.UpdateCommentRq;

public interface CommentService {

    Comment save(CreateCommentRq createCommentRq);

    Comment getById(String id);

    Comment update(UpdateCommentRq updateCommentRq);

    Page<Comment> getAll(Integer pageSize, Integer pageNumber);

    void deleteById(String id);
}
