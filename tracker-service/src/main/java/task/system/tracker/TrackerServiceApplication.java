package task.system.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TrackerServiceApplication {

    public static void main(String[] args) {

        Integer x = 5;
        x = 6;
        System.out.println(x);


        SpringApplication.run(TrackerServiceApplication.class, args);
    }

}
