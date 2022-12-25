package task.system.tracker.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import task.system.tracker.domain.Workload;
import task.system.tracker.dto.page.PageDto;
import task.system.tracker.dto.workload.CreateWorkloadRq;
import task.system.tracker.dto.workload.UpdateWorkloadRq;
import task.system.tracker.dto.workload.WorkloadDto;
import task.system.tracker.service.workload.WorkloadService;
import task.system.tracker.util.PageSizeValidator;

@RestController
@RequestMapping(
        value = "/workload",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = "Workload Controller")
@RequiredArgsConstructor
public class RestWorkloadController {

    private final WorkloadService workloadService;
    private final PageSizeValidator pageSizeValidator;

    @PostMapping("/save")
    @ApiOperation(value = "Save workload", response = WorkloadDto.class)
    public ResponseEntity<WorkloadDto> createWorkload(@Validated @RequestBody CreateWorkloadRq createWorkloadRq) {
        return ResponseEntity.ok(new WorkloadDto(workloadService.save(createWorkloadRq)));
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update workload", response = WorkloadDto.class)
    public ResponseEntity<WorkloadDto> updateWorkload(@Validated @RequestBody UpdateWorkloadRq updateWorkloadRq) {
        return ResponseEntity.ok(new WorkloadDto(workloadService.update(updateWorkloadRq)));
    }

    @GetMapping()
    @ApiOperation(value = "Get workload by id", response = WorkloadDto.class)
    public ResponseEntity<WorkloadDto> getById(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok(new WorkloadDto(workloadService.getById(id)));
    }

    @GetMapping("/getPage")
    @ApiOperation(value = "Get page with workloads")
    public ResponseEntity<PageDto<WorkloadDto>> getPage (
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Integer correctPageSize = pageSizeValidator.getPageSize(pageSize);
        Page<Workload> workloadPage = workloadService.getAll(correctPageSize, pageNumber);
        return ResponseEntity.ok(new PageDto<>(WorkloadDto.toDtoList(workloadPage.getContent()),
                pageNumber, correctPageSize, workloadPage.getTotalPages()));
    }

    @DeleteMapping()
    public void deleteWorkload(@RequestParam(value = "id") String id) {
        workloadService.deleteById(id);
    }




}
