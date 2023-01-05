package task.system.tracker.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "sprint")
@EqualsAndHashCode(exclude = {"superSprint", "histories"}, callSuper = true)
@NoArgsConstructor
public class Sprint extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @ManyToOne
    @JoinColumn(name = "super_sprint_id")
    private SuperSprint superSprint;

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.REMOVE)
    private Set<History> histories;


    public Sprint(String author, String name, LocalDateTime startAt, LocalDateTime endAt) {
        this.author = author;
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public Sprint(String id, String author, String name, LocalDateTime startAt, LocalDateTime endAt) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;

    }
}
