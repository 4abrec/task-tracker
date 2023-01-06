package task.system.tracker.dto.label;

import lombok.Data;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.Label;
import task.system.tracker.dto.task.TaskDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class LabelDto {

    private String id;
    private String text;
    private String authorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private TaskDto taskDto;

    public LabelDto(Label label) {
        this.id = label.getId();
        this.text = label.getText();
        this.authorId = label.getAuthor();
        this.createdAt = label.getCreatedAt();
        this.updatedAt = label.getUpdatedAt();
        this.taskDto = new TaskDto(label.getTask());
    }

    public static List<LabelDto> toDtoList(List<Label> labelList) {
        return labelList.stream()
                .map(LabelDto::new)
                .collect(Collectors.toList());
    }
}
