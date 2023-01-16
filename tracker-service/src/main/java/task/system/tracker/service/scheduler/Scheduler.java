package task.system.tracker.service.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import task.system.tracker.dto.notification.NotificationDto;
import task.system.tracker.service.bug.BugService;
import task.system.tracker.service.kafka.RabbitProducerNotificationService;
import task.system.tracker.service.notification.NotificationSubscriptionService;
import task.system.tracker.service.task.TaskService;

@Component
@RequiredArgsConstructor
@Slf4j
public class Scheduler {

    private final TaskService taskService;

    private final BugService bugService;

    private final NotificationSubscriptionService notificationSubscriptionService;

    private final RabbitProducerNotificationService rabbitProducerNotificationService;

    //private final SchedulerinfoService schedulerinfoService;


    @Scheduled(cron = "${cron.scheduler}")
    public void launch() {

        sendMassageToKafka("kdjw", String.format("Новый баг: (%s) %s", 1, 1));
        //LocalDateTime lastStartTime = schedulerinfoService.getById(ID).getLastAt();
        //notificationSubscriptionService.getAll().forEach(subscription -> process(subscription, lastStartTime));
    }

//    private void process(NotificationSubscriptionEntity subscription, LocalDateTime lastStartTime) {
//        bugService.findByProjectIdAndCreatedAt(subscription.getProjectId(), lastStartTime)
//                .forEach(bug -> sendMessageToKafkaBug(bug, subscription.getUserId()));
//
//        taskService.findByProjectIdAndCreatedAt(subscription.getProjectId(), lastStartTime)
//                .forEach(task -> sendMassageToKafkaTask(task, subscription.getUserId()));
//    }
//
    private void sendMassageToKafka(String userId, String text) {
        try {
            rabbitProducerNotificationService.send(new NotificationDto(text, userId));
        } catch (JsonProcessingException exp) {
            log.info("Ошибка при обработке (анализе, генерации) содержимого JSON", exp);
        }
    }
//
//    private void sendMessageToKafkaBug(BugEntity bugEntity, String userId) {
//        sendMassageToKafka(userId, String.format("Новый баг: (%s) %s", bugEntity.getId(), bugEntity.getName()));
//    }
//
//    private void sendMassageToKafkaTask(TaskEntity taskEntity, String userId) {
//        sendMassageToKafka(userId, String.format("Новая задача: (%s) %s", taskEntity.getId(), taskEntity.getName()));
//    }
}
