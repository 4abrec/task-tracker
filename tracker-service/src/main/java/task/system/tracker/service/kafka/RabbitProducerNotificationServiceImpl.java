package task.system.tracker.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import task.system.tracker.dto.notification.NotificationDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitProducerNotificationServiceImpl implements RabbitProducerNotificationService {

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(NotificationDto notification) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(notification);
        rabbitTemplate.convertAndSend(exchange, routingKey, json);
        log.info(json);
    }
}
