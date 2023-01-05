package task.system.tracker.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "history")
@Data
@EqualsAndHashCode(exclude = {"epic", "sprint", "bugs", "tasks"}, callSuper = true)
@NoArgsConstructor
public class History extends BaseEntity implements Serializable {

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

    @ManyToOne
    @JoinColumn(name = "epic_id")
    private Epic epic;

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    @OneToMany(mappedBy = "history", cascade = CascadeType.REMOVE)
    private Set<Bug> bugs;

    @OneToMany(mappedBy = "history", cascade = CascadeType.REMOVE)
    private Set<Task> tasks;

    public History(String author, String name, String description, ETaskStatus status, EPriority priority) {
        this.author = author;
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public History(String id, String author, String name, String description, ETaskStatus status, EPriority priority) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }
}
