package task.system.tracker.dto.task;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.Bug;
import task.system.tracker.domain.EPriority;
import task.system.tracker.domain.ETaskStatus;
import task.system.tracker.domain.Task;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateTaskRq {

    @NotBlank
    @Size(max = 128)
    @ApiModelProperty(value = "name", required = true)
    protected String name;

    @NotBlank
    @Size(max = 128)
    @ApiModelProperty(value = "description", required = true)
    protected String description;

    @NotBlank
    @Size(max = 18)
    @ApiModelProperty(value = "status", required = true, allowableValues = "BACKLOG, IN_PROGRESS, PAUSED, DONE")
    protected String status;

    @NotBlank
    @Size(max = 18)
    @ApiModelProperty(value = "priority", required = true, allowableValues = "MINOR, MAJOR, CRITICAL, BLOCKER")
    protected String priority;

    @NotBlank
    @Size(max = 36)
    @ApiModelProperty(value = "history_id", required = true)
    protected String historyId;

    @NotBlank
    @Size(max = 36)
    @ApiModelProperty(value = "author_id", required = true)
    protected String authorId;

    @NotBlank
    @Size(max = 36)
    @ApiModelProperty(value = "assignee_id", required = true)
    protected String assigneeId;

    public Task toEntity() {
        return new Task(authorId, assigneeId, name, description, ETaskStatus.valueOf(status), EPriority.valueOf(priority));
    }
}
