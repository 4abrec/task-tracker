package task.system.tracker.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.system.tracker.service.epic.EpicService;
import task.system.tracker.util.PageSizeValidator;

@RestController
@RequestMapping( "/api/sprint")
@Api(tags = "Epic Controller")
@RequiredArgsConstructor
public class RestEpicController {

    private final EpicService epicService;
    private final PageSizeValidator pageSizeValidator;
}
