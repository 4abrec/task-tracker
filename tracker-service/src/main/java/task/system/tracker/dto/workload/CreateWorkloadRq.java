package task.system.tracker.dto.workload;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.EActivity;
import task.system.tracker.domain.Workload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateWorkloadRq {

    @NotBlank
    @Size(max = 128)
    @ApiModelProperty(value = "name", required = true)
    protected String name;

    @NotBlank
    @Size(max = 18)
    @ApiModelProperty(value = "activity", required = true, allowableValues = "ACTIVE, INACTIVE")
    protected String activity;

    @NotBlank
    @Size(max = 36)
    @ApiModelProperty(value = "author_id", required = true)
    protected String authorId;

    public Workload toEntity() {
        return new Workload(authorId, name, EActivity.valueOf(activity));
    }
}
