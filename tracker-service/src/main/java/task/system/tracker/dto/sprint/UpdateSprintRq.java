package task.system.tracker.dto.sprint;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.Sprint;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateSprintRq extends CreateSprintRq{

    @NotBlank
    @ApiModelProperty(name = "id", required = true)
    private String id;

    @Override
    public Sprint toEntity() {
        return new Sprint(id, authorId, name, startAt, endAt);
    }

}
