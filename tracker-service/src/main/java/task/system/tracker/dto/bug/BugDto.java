package task.system.tracker.dto.bug;

import lombok.Data;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.Bug;
import task.system.tracker.domain.EPriority;
import task.system.tracker.domain.ETaskStatus;
import task.system.tracker.dto.history.HistoryDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class BugDto {

    private String id;
    private String name;
    private String description;
    private ETaskStatus status;
    private EPriority priority;
    private String authorId;
    private String assigneeId;
    private HistoryDto historyDto;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BugDto(Bug bug) {
        this.id = bug.getId();
        this.name = bug.getName();
        this.description = bug.getDescription();
        this.status = bug.getStatus();
        this.priority = bug.getPriority();
        this.authorId = bug.getAuthor();
        this.assigneeId = bug.getAssignee();
        this.historyDto = new HistoryDto(bug.getHistory());
        this.createdAt = bug.getCreatedAt();
        this.updatedAt = bug.getUpdatedAt();
    }

    public static List<BugDto> toDtoList(List<Bug> bugList) {
        return bugList.stream()
                .map(BugDto::new)
                .collect(Collectors.toList());
    }
}
