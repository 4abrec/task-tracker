package task.system.tracker.dto.label;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.Label;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CreateLabelRq {

    @NotBlank
    @ApiModelProperty(value = "text")
    protected String text;

    @NotBlank
    @ApiModelProperty(value = "author_id")
    protected String authorId;

    @NotBlank
    @ApiModelProperty(value = "task_id")
    protected String taskId;

    public Label toEntity() {
        return new Label(authorId, text);
    }
}
