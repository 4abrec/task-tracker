package task.system.tracker.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import task.system.tracker.dto.notification.NotificationDto;

public interface RabbitProducerNotificationService {

    void send(NotificationDto notificationDto) throws JsonProcessingException;
}
