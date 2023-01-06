package task.system.tracker.dto.comment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.Comment;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateCommentRq extends CreateCommentRq {

    @NotBlank
    @ApiModelProperty(value = "id")
    private String id;

    @Override
    public Comment toEntity() {
        return new Comment(id, authorId, text);
    }
}
