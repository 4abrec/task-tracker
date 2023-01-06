package task.system.tracker.dto.comment;

import lombok.Data;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.Comment;
import task.system.tracker.dto.task.TaskDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CommentDto {

    private String id;
    private String text;
    private String authorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private TaskDto taskDto;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.authorId = comment.getAuthor();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
        this.taskDto = new TaskDto(comment.getTask());
    }

    public static List<CommentDto> toDtoList(List<Comment> commentList) {
        return commentList.stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());
    }
}
