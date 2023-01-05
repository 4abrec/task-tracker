package task.system.tracker.dto.history;

import lombok.Data;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.EPriority;
import task.system.tracker.domain.ETaskStatus;
import task.system.tracker.domain.History;
import task.system.tracker.dto.epic.EpicDto;
import task.system.tracker.dto.sprint.SprintDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class HistoryDto {

    private String id;
    private String name;
    private String description;
    private String authorId;
    private ETaskStatus status;
    private EPriority priority;
    private SprintDto sprintDto;
    private EpicDto epicDto;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public HistoryDto(History history) {
        this.id = history.getId();
        this.name = history.getName();
        this.description = history.getDescription();
        this.authorId = history.getAuthor();;
        this.status = history.getStatus();
        this.priority = history.getPriority();
        this.sprintDto = new SprintDto(history.getSprint());
        this.epicDto = new EpicDto(history.getEpic());
        this.createdAt = history.getCreatedAt();
        this.updatedAt = history.getUpdatedAt();
    }

    public static List<HistoryDto> toDtoList(List<History> historyDtoList) {
        return historyDtoList.stream()
                .map(HistoryDto::new)
                .collect(Collectors.toList());
    }
}
