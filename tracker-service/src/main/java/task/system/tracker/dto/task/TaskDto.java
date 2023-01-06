package task.system.tracker.dto.task;

import lombok.Data;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.EPriority;
import task.system.tracker.domain.ETaskStatus;
import task.system.tracker.domain.Task;
import task.system.tracker.dto.history.HistoryDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TaskDto {

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

    public TaskDto(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.description = task.getDescription();
        this.status = task.getStatus();
        this.priority = task.getPriority();
        this.authorId = task.getAuthor();
        this.assigneeId = task.getAssignee();
        this.historyDto = new HistoryDto(task.getHistory());
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
    }

    public static List<TaskDto> toDtoList(List<Task> taskList) {
        return taskList.stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
    }
}
