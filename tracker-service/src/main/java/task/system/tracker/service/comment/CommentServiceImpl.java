package task.system.tracker.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import task.system.tracker.domain.Comment;
import task.system.tracker.domain.Task;
import task.system.tracker.dto.comment.CreateCommentRq;
import task.system.tracker.dto.comment.UpdateCommentRq;
import task.system.tracker.exception.ResourceNotFoundException;
import task.system.tracker.repository.CommentRepository;
import task.system.tracker.service.task.TaskService;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final TaskService taskService;
    private final CommentRepository commentRepository;

    @Override
    public Comment save(CreateCommentRq createCommentRq) {
        Task task = taskService.getById(createCommentRq.getTaskId());
        Comment comment = createCommentRq.toEntity();
        comment.setTask(task);
        return commentRepository.save(comment);
    }

    @Override
    public Comment getById(String id) {
        return commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Comment with id %s not found", id)));
    }

    @Override
    public Comment update(UpdateCommentRq updateCommentRq) {
        Comment entityCommentFromDb = getById(updateCommentRq.getId());
        Comment entityCommentForUpd = updateCommentRq.toEntity();
        entityCommentForUpd.setTask(taskService.getById(updateCommentRq.getTaskId()));
        entityCommentForUpd.setCreatedAt(entityCommentFromDb.getCreatedAt());
        return commentRepository.save(entityCommentForUpd);
    }

    @Override
    public Page<Comment> getAll(Integer pageSize, Integer pageNumber) {
        return commentRepository.findAll(Pageable.ofSize(pageSize).withPage(pageNumber));
    }

    @Override
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }
}
