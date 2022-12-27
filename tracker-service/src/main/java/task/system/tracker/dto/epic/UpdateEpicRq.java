package task.system.tracker.dto.epic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.EPriority;
import task.system.tracker.domain.ETaskStatus;
import task.system.tracker.domain.Epic;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateEpicRq extends CreateEpicRq {

    @NotBlank
    @ApiModelProperty(name = "id", required = true)
    private String id;

    @Override
    public Epic toEntity() {
        return new Epic(id, authorId, name, description, ETaskStatus.valueOf(status), EPriority.valueOf(priority));
    }
}
