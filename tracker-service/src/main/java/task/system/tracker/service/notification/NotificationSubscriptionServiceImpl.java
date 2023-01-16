package task.system.tracker.service.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import task.system.tracker.domain.NotificationSubscription;
import task.system.tracker.dto.notification.NotificationSubscriptionRq;
import task.system.tracker.exception.ResourceNotFoundException;
import task.system.tracker.repository.NotificationSubscriptionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationSubscriptionServiceImpl implements NotificationSubscriptionService{

    private final NotificationSubscriptionRepository notificationSubscriptionRepository;

    @Override
    public NotificationSubscription subscribe(NotificationSubscriptionRq dto) {
        return notificationSubscriptionRepository.save(dto.toEntity());
    }

    @Override
    public NotificationSubscription unsubscribe(NotificationSubscriptionRq dto) {
        NotificationSubscription notifySubscription = getByUserIdAndProjectId(dto);
        notificationSubscriptionRepository.delete(notifySubscription);
        return notifySubscription;
    }

    @Override
    public NotificationSubscription getByUserIdAndProjectId(NotificationSubscriptionRq dto) {
        String errorMessage = String.format("Subscription to project [%s] for user [%s] not found",
                dto.getProjectId(), dto.getSubscriberId());

        return notificationSubscriptionRepository.findBySubscriberIdAndProjectId(dto.getSubscriberId(), dto.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }

    @Override
    public Page<NotificationSubscription> getAll(Integer pageSize, Integer pageNumber) {
        return notificationSubscriptionRepository.findAll(Pageable
                .ofSize(pageSize)
                .withPage(pageNumber));
    }

    @Override
    public List<NotificationSubscription> getAll() {
        return notificationSubscriptionRepository.findAll();
    }

    @Override
    public Page<NotificationSubscription> getByUserId(String userId, Integer pageSize, Integer pageNumber) {
        return notificationSubscriptionRepository.findAllBySubscriberId(userId, Pageable
                .ofSize(pageSize)
                .withPage(pageNumber));
    }
}
