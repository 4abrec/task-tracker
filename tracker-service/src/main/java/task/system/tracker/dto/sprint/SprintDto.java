package task.system.tracker.dto.sprint;

import lombok.Data;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.Sprint;
import task.system.tracker.dto.supersprint.SuperSprintDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class SprintDto {

    private String id;
    private String name;
    private String authorId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SuperSprintDto superSprintDto;

    public SprintDto(Sprint sprint) {
        this.id = sprint.getId();
        this.name = sprint.getName();
        this.authorId = sprint.getAuthor();
        this.startAt = sprint.getStartAt();
        this.endAt = sprint.getEndAt();
        this.createdAt = sprint.getCreatedAt();
        this.updatedAt = sprint.getUpdatedAt();
        this.superSprintDto = new SuperSprintDto(sprint.getSuperSprint());
    }

    public static List<SprintDto> toDtoList(List<Sprint> sprintList) {
        return sprintList.stream()
                .map(SprintDto::new).collect(Collectors.toList());
    }
}
