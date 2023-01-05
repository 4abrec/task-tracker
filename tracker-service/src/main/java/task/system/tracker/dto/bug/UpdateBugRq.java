package task.system.tracker.dto.bug;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.Bug;
import task.system.tracker.domain.EPriority;
import task.system.tracker.domain.ETaskStatus;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateBugRq extends CreateBugRq{

    @NotBlank
    @ApiModelProperty(name = "id", required = true)
    private String id;

    @Override
    public Bug toEntity() {
        return new Bug(id, authorId, assigneeId, name, description, ETaskStatus.valueOf(status), EPriority.valueOf(priority));
    }
}
