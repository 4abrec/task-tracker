package task.system.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
public class TrackerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackerServiceApplication.class, args);
    }

}
