package task.system.tracker.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "super_sprint")
@Data
@EqualsAndHashCode(exclude = {"sprints", "epics"}, callSuper = true)
public class SuperSprint extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "superSprint")
    private Set<Sprint> sprints;

    @OneToMany(mappedBy = "superSprint")
    private Set<Epic> epics;
}
