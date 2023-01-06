package task.system.tracker.dto.label;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.Label;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateLabelRq extends CreateLabelRq {

    @NotBlank
    @ApiModelProperty(value = "id")
    private String id;

    @Override
    public Label toEntity() {
        return new Label(id, authorId, text);
    }
}
