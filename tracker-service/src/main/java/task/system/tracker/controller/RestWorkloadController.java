package task.system.tracker.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import task.system.tracker.dto.workload.CreateWorkloadRq;
import task.system.tracker.dto.workload.UpdateWorkloadRq;
import task.system.tracker.dto.workload.WorkloadDto;
import task.system.tracker.service.WorkloadService;

@RestController
@RequestMapping("/workload")
@Api(tags = "Workload Controller")
@RequiredArgsConstructor
public class RestWorkloadController {

    private final WorkloadService workloadService;

    @PostMapping("/create")
    public ResponseEntity<WorkloadDto> createWorkload(@Validated @RequestBody CreateWorkloadRq createWorkloadRq) {
        return new ResponseEntity<>(new WorkloadDto(workloadService.save(createWorkloadRq)), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<WorkloadDto> updateWorkload(@Validated @RequestBody UpdateWorkloadRq updateWorkloadRq) {
        return new ResponseEntity<>(new WorkloadDto(workloadService.update(updateWorkloadRq)), HttpStatus.OK);
    }


}
