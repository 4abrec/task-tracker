package task.system.tracker.dto.project;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.EActivity;
import task.system.tracker.domain.Project;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateProjectRq {

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
    @ApiModelProperty(value = "activity", required = true, allowableValues = "ACTIVE, INACTIVE")
    protected String activity;

    @NotBlank
    @Size(max = 36)
    @ApiModelProperty(value = "workload_id", required = true)
    protected String workloadId;

    @NotBlank
    @Size(max = 36)
    @ApiModelProperty(value = "author_id", required = true)
    protected String authorId;

    public Project toEntity() {

        return new Project(authorId, name, description, EActivity.valueOf(activity));
    }
}
