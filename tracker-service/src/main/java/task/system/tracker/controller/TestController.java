package task.system.tracker.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "dw")
@Slf4j
public class TestController {

    @GetMapping("/mama")
    @Secured("ROLE_USER")
    public String test() {
        return "mama";
    }
}
