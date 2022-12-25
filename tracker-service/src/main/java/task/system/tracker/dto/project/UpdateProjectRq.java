package task.system.tracker.dto.project;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.EActivity;
import task.system.tracker.domain.Project;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateProjectRq extends CreateProjectRq{

    @NotBlank
    @ApiModelProperty(name = "id", required = true)
    private String id;

    @Override
    public Project toEntity() {
        return new Project(id, authorId, name, description, EActivity.valueOf(activity));
    }
}
