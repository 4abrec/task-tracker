package task.system.tracker.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "super_sprint")
@Data
@EqualsAndHashCode(exclude = {"sprints", "epics"}, callSuper = true)
@NoArgsConstructor
public class SuperSprint extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @OneToMany(mappedBy = "superSprint", cascade = CascadeType.REMOVE)
    private Set<Sprint> sprints;

    @OneToMany(mappedBy = "superSprint", cascade = CascadeType.REMOVE)
    private Set<Epic> epics;

    public SuperSprint(String authorId, String name, LocalDateTime startAt, LocalDateTime endAt) {
        this.author = authorId;
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public SuperSprint(String id, String authorId, String name, LocalDateTime startAt, LocalDateTime endAt) {
        this.id = id;
        this.author = authorId;
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
    }

}
