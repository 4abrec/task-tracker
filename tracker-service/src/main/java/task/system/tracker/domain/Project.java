package task.system.tracker.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "project")
@Data
@EqualsAndHashCode(exclude = {"workload", "epics"}, callSuper = true)
@NoArgsConstructor
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

    public Project(String author, String name, String description, EActivity activity) {
        this.author = author;
        this.name = name;
        this.description = description;
        this.activity = activity;
    }

    public Project(String id, String author, String name, String description, EActivity activity) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.description = description;
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", activity=" + activity +
                '}';
    }
}
