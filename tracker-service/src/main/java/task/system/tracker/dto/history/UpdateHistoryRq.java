package task.system.tracker.dto.history;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.EPriority;
import task.system.tracker.domain.ETaskStatus;
import task.system.tracker.domain.History;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateHistoryRq extends CreateHistoryRq {

    @NotBlank
    @ApiModelProperty(name = "id", required = true)
    private String id;

    @Override
    public History toEntity() {
        return new History(id, authorId, name, description, ETaskStatus.valueOf(status), EPriority.valueOf(priority));
    }
}
