package task.system.tracker.dto.supersprint;

import lombok.Data;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.SuperSprint;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class SuperSprintDto {

    private String id;
    private String name;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String authorId;

    public SuperSprintDto(SuperSprint superSprint) {
        this.id = superSprint.getId();
        this.name = superSprint.getName();
        this.startAt = superSprint.getStartAt();
        this.endAt = superSprint.getEndAt();
        this.createdAt = superSprint.getCreatedAt();
        this.updatedAt = superSprint.getUpdatedAt();
        this.authorId = superSprint.getAuthor();
    }

    public static List<SuperSprintDto> toDtoList(List<SuperSprint> superSprintList) {
        return superSprintList.stream()
                .map(SuperSprintDto::new)
                .collect(Collectors.toList());
    }
}
