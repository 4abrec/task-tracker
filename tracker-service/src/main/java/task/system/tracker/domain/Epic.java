package task.system.tracker.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "epic")
@Data
@EqualsAndHashCode(exclude = {"project", "superSprint", "histories"}, callSuper = true)
@NoArgsConstructor
public class Epic extends BaseEntity implements Serializable {

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
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "super_sprint_id")
    private SuperSprint superSprint;

    @OneToMany(mappedBy = "epic")
    private Set<History> histories;

    public Epic(String author, String name, String description, ETaskStatus status, EPriority priority) {
        this.author = author;
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public Epic(String id, String author, String name, String description, ETaskStatus status, EPriority priority) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }
}
