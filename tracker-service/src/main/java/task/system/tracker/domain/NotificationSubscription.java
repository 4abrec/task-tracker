package task.system.tracker.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification_subscription")
@EqualsAndHashCode(callSuper = true)
public class NotificationSubscription extends BaseEntity {

    @Column(name = "subscriber_id")
    private String subscriberId;

    @Column(name = "project_id")
    private String projectId;

    public NotificationSubscription(String subscriberId, String projectId, String authorId) {
        this.subscriberId = subscriberId;
        this.projectId = projectId;
        this.author = authorId;
    }
}
