package task.system.tracker.dto.comment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.Comment;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CreateCommentRq {

    @NotBlank
    @ApiModelProperty(value = "text")
    protected String text;

    @NotBlank
    @ApiModelProperty(value = "author_id")
    protected String authorId;

    @NotBlank
    @ApiModelProperty(value = "task_id")
    protected String taskId;

    public Comment toEntity() {
        return new Comment(authorId, text);
    }
}
