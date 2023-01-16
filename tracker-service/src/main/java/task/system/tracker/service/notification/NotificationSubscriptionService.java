package task.system.tracker.service.notification;

import org.springframework.data.domain.Page;
import task.system.tracker.domain.NotificationSubscription;
import task.system.tracker.dto.notification.NotificationSubscriptionRq;

import java.util.List;

public interface NotificationSubscriptionService {

    NotificationSubscription subscribe(NotificationSubscriptionRq dto);

    NotificationSubscription unsubscribe(NotificationSubscriptionRq dto);

    NotificationSubscription getByUserIdAndProjectId(NotificationSubscriptionRq dto);

    Page<NotificationSubscription> getAll(Integer pageSize, Integer pageNumber);

    List<NotificationSubscription> getAll();

    Page<NotificationSubscription> getByUserId(String userId, Integer pageSize, Integer pageNumber);
}
