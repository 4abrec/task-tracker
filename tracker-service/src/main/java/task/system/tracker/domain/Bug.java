package task.system.tracker.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bug")
@Data
@EqualsAndHashCode(exclude = {"history"}, callSuper = true)
@NoArgsConstructor
public class Bug extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ETaskStatus status;

    @Column(name = "priority", nullable = false)
    @Enumerated(EnumType.STRING)
    private EPriority priority;

    @Column(name = "assignee_id", length = 36, nullable = false)
    private String assignee;

    @ManyToOne
    @JoinColumn(name = "history_id", nullable = false)
    private History history;

    public Bug(String authorId, String assigneeId, String name, String description, ETaskStatus status, EPriority priority) {
        this.author = authorId;
        this.assignee = assigneeId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public Bug(String id, String authorId, String assigneeId, String name, String description, ETaskStatus status, EPriority priority) {
        this.id = id;
        this.author = authorId;
        this.assignee = assigneeId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }
}
