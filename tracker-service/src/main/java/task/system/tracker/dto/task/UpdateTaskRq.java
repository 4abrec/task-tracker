package task.system.tracker.dto.task;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.Bug;
import task.system.tracker.domain.EPriority;
import task.system.tracker.domain.ETaskStatus;
import task.system.tracker.domain.Task;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateTaskRq extends CreateTaskRq{


    @NotBlank
    @ApiModelProperty(name = "id", required = true)
    private String id;

    @Override
    public Task toEntity() {
        return new Task(id, authorId, assigneeId, name, description, ETaskStatus.valueOf(status), EPriority.valueOf(priority));
    }
}
