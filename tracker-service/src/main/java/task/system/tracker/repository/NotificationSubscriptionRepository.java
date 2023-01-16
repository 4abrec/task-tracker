package task.system.tracker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.system.tracker.domain.NotificationSubscription;

import java.util.Optional;

@Repository
public interface NotificationSubscriptionRepository extends JpaRepository<NotificationSubscription, String> {

    Optional<NotificationSubscription> findBySubscriberIdAndProjectId(String userId, String projectId);

    Page<NotificationSubscription> findAllBySubscriberId(String userId, Pageable pageable);
}
