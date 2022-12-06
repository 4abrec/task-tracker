package task.system.tracker.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
    private String name;

    @NotBlank
    @Size(max = 18)
    @ApiModelProperty(value = "activity", required = true, allowableValues = "ACTIVE, INACTIVE")
    private String activity;

    @NotBlank
    @Size(max = 36)
    @ApiModelProperty(value = "author_id", required = true)
    private String authorId;

    public Workload toEntity() {
        return new Workload(authorId, name, EActivity.valueOf(activity));
    }
}
