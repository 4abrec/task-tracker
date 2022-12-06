package task.system.tracker.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "project")
@Data
@EqualsAndHashCode(exclude = {"workload", "epics"}, callSuper = true)
public class Project extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "description", nullable = false, length = 128)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity", length = 18, nullable = false)
    private EActivity activity;

    @ManyToOne
    @JoinColumn(name = "workload_id", nullable = false)
    private Workload workload;

    @OneToMany(mappedBy = "project")
    private Set<Epic> epics;
}
