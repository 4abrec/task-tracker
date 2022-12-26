package task.system.tracker.dto.supersprint;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.SuperSprint;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateSuperSprintRq extends CreateSuperSprintRq {

    @NotBlank
    @ApiModelProperty(name = "id", required = true)
    private String id;

    @Override
    public SuperSprint toEntity() {
        return new SuperSprint(id, authorId, name, startAt, endAt);
    }
}
