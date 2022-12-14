package task.system.tracker.dto.project;

import lombok.Data;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.EActivity;
import task.system.tracker.domain.Project;
import task.system.tracker.dto.workload.WorkloadDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProjectDto {

    private String id;
    private String description;
    private String authorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private EActivity activity;
    private WorkloadDto workloadDto;

    public ProjectDto(Project project) {
        this.id = project.getId();
        this.description = project.getDescription();
        this.authorId = project.getAuthor();
        this.createdAt  = project.getCreatedAt();
        this.updatedAt = project.getUpdatedAt();
        this.name = project.getName();
        this.activity = project.getActivity();
        this.workloadDto = new WorkloadDto(project.getWorkload());
    }

    public static List<ProjectDto> toDtoList(List<Project> projectList) {
        return projectList.stream()
                .map(ProjectDto::new)
                .collect(Collectors.toList());
    }
}
