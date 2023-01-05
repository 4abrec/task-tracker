package task.system.tracker.dto.epic;

import lombok.Data;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.EPriority;
import task.system.tracker.domain.ETaskStatus;
import task.system.tracker.domain.Epic;
import task.system.tracker.dto.project.ProjectDto;
import task.system.tracker.dto.supersprint.SuperSprintDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class EpicDto {

    private String id;
    private String name;
    private String description;
    private ETaskStatus status;
    private EPriority priority;
    private String authorId;
    private ProjectDto projectDto;
    private SuperSprintDto superSprintDto;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EpicDto(Epic epic) {
        this.id = epic.getId();
        this.name = epic.getName();
        this.description = epic.getDescription();
        this.status = epic.getStatus();
        this.priority = epic.getPriority();
        this.authorId = epic.getAuthor();
        this.projectDto = new ProjectDto(epic.getProject());
        this.superSprintDto = new SuperSprintDto(epic.getSuperSprint());
        this.createdAt = epic.getCreatedAt();
        this.updatedAt = epic.getUpdatedAt();
    }

    public static List<EpicDto> toDtoList(List<Epic> epics) {
        return epics.stream()
                .map(EpicDto::new).collect(Collectors.toList());
    }
}
