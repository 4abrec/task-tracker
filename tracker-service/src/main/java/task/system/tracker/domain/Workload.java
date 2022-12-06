package task.system.tracker.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "workload")
@Data
@EqualsAndHashCode(exclude = {"projects"}, callSuper = true)
@NoArgsConstructor
public class Workload extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity", length = 18, nullable = false)
    private EActivity activity;

    @OneToMany(mappedBy = "workload")
    private Set<Project> projects;

    public Workload(String author, String name, EActivity activity) {
        setAuthor(author);
        this.name = name;
        this.activity = activity;
    }
}
