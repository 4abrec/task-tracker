package task.system.tracker.dto.supersprint;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.SuperSprint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CreateSuperSprintRq {

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

    public SuperSprint toEntity() {
        return new SuperSprint(authorId, name, startAt, endAt);
    }
}
