package task.system.tracker.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "sprint")
@EqualsAndHashCode(exclude = {"superSprint", "histories", "bugs"}, callSuper = true)
public class Sprint extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "super_sprint_id")
    private SuperSprint superSprint;

    @OneToMany(mappedBy = "sprint")
    private Set<History> histories;

    @OneToMany(mappedBy = "sprint")
    private Set<Bug> bugs;


}
