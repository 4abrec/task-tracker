package task.system.tracker.dto.workload;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UpdateWorkloadRq extends CreateWorkloadRq{

    @NotBlank
    private String id;

    public UpdateWorkloadRq(String id) {
        super();
        this.id = id;
    }
}
