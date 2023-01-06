package task.system.tracker.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "label")
@Data
@EqualsAndHashCode(exclude = {"task"}, callSuper = true)
@NoArgsConstructor
public class Label extends BaseEntity implements Serializable {

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Label(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public Label(String id, String author, String text) {
        this.id = id;
        this.author = author;
        this.text = text;
    }
}
