package task.system.tracker.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "comment")
@Data
@EqualsAndHashCode(exclude = "task", callSuper = true)
public class Comment extends BaseEntity implements Serializable {

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
