package task.system.tracker.dto.notification;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import task.system.tracker.domain.NotificationSubscription;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationSubscriptionRq {
    @NotNull
    @Size(max = 36)
    @ApiModelProperty(value = "Subscriber d", required = true)
    private String subscriberId;

    @NotNull
    @Size(max = 36)
    @ApiModelProperty(value = "Project id", required = true)
    private String projectId;

    @NotNull
    @Size(max = 36)
    @ApiModelProperty(value = "Author id", required = true)
    private String authorId;

    public NotificationSubscription toEntity() {
        return new NotificationSubscription(subscriberId, projectId, authorId);
    }
}
