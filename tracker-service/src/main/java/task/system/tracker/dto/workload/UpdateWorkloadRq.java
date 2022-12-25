package task.system.tracker.dto.workload;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.EActivity;
import task.system.tracker.domain.Workload;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UpdateWorkloadRq extends CreateWorkloadRq{

    @NotBlank
    private String id;

    @Override
    public Workload toEntity() {
        return new Workload(id, authorId, name, EActivity.valueOf(activity));
    }
}
