package task.system.tracker.dto.sprint;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.Sprint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CreateSprintRq {

    @NotBlank
    @Size(max = 128)
    @ApiModelProperty(value = "name", required = true)
    protected String name;

    @ApiModelProperty(value = "startAt", required = true)
    protected LocalDateTime startAt;

    @ApiModelProperty(value = "endAt", required = true)
    protected LocalDateTime endAt;

    @NotBlank
    @Size(max = 36)
    @ApiModelProperty(value = "author_id", required = true)
    protected String authorId;

    @NotBlank
    @Size(max = 36)
    @ApiModelProperty(value = "super_sprint_id", required = true)
    protected String superSprintId;

    public Sprint toEntity() {
        return new Sprint(authorId, name, startAt, endAt);
    }
}
