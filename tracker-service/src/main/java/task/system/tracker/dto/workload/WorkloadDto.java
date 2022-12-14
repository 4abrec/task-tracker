package task.system.tracker.dto.workload;

import lombok.Data;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.EActivity;
import task.system.tracker.domain.Workload;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class WorkloadDto {

    private String id;
    private String authorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private EActivity activity;

    public WorkloadDto(Workload workload) {
        this.id = workload.getId();
        this.authorId = workload.getAuthor();
        this.createdAt  = workload.getCreatedAt();
        this.updatedAt = workload.getUpdatedAt();
        this.name = workload.getName();
        this.activity = workload.getActivity();
    }

    public static List<WorkloadDto> toDtoList(List<Workload> workloadList) {
        return workloadList.stream()
                .map(WorkloadDto::new)
                .collect(Collectors.toList());
    }

}
