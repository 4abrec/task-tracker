package task.system.tracker.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "task")
@Data
@EqualsAndHashCode(exclude = {"history", "labels", "comments"}, callSuper = true)
public class Task extends BaseEntity implements Serializable {

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

    @OneToMany(mappedBy = "task")
    private Set<Label> labels;

    @OneToMany(mappedBy = "task")
    private Set<Comment> comments;
}
