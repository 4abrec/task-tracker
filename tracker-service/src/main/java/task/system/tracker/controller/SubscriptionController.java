package task.system.tracker.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.system.tracker.domain.NotificationSubscription;
import task.system.tracker.dto.notification.NotificationSubscriptionRq;
import task.system.tracker.service.notification.NotificationSubscriptionService;

@RestController
@RequestMapping(
        value = "/api/subscription")
@Api(tags = "Subscription Controller")
@RequiredArgsConstructor
public class SubscriptionController {

    private final NotificationSubscriptionService notificationSubscriptionService;

    @PostMapping("/subscribe")
    public ResponseEntity<NotificationSubscription> subscribe(@RequestBody NotificationSubscriptionRq notificationSubscriptionRq) {
        return ResponseEntity.ok(notificationSubscriptionService.subscribe(notificationSubscriptionRq));
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<NotificationSubscription> unsubscribe(@RequestBody NotificationSubscriptionRq notificationSubscriptionRq) {
        return ResponseEntity.ok(notificationSubscriptionService.unsubscribe(notificationSubscriptionRq));
    }
}
