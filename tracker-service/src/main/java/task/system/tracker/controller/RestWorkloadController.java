package task.system.tracker.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.system.tracker.domain.Workload;
import task.system.tracker.dto.CreateWorkloadRq;
import task.system.tracker.dto.WorkloadDto;
import task.system.tracker.service.WorkloadService;

@RestController
@RequestMapping("/workload")
@Api(tags = "Workload Controller")
@RequiredArgsConstructor
public class RestWorkloadController {

    private final WorkloadService workloadService;

    @PostMapping("/create")
    public ResponseEntity<WorkloadDto> createWorkload(@Validated @RequestBody CreateWorkloadRq createWorkloadRq) {
        return new ResponseEntity<>(new WorkloadDto(workloadService.save(createWorkloadRq)), HttpStatus.OK);
    }


}