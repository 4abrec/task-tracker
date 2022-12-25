package task.system.tracker.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import task.system.tracker.dto.project.CreateProjectRq;
import task.system.tracker.dto.project.ProjectDto;
import task.system.tracker.dto.project.UpdateProjectRq;
import task.system.tracker.dto.workload.WorkloadDto;
import task.system.tracker.service.project.ProjectService;

@RestController
@RequestMapping(
        value = "/project",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = "Project Controller")
@RequiredArgsConstructor
public class RestProjectController {

    private final ProjectService projectService;

    @PostMapping("/save")
    @ApiOperation(value = "Save project", response = ProjectDto.class)
    public ResponseEntity<ProjectDto> createWorkload(@Validated @RequestBody CreateProjectRq createProjectRq) {
        return ResponseEntity.ok(new ProjectDto((projectService.save(createProjectRq))));
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update project", response = WorkloadDto.class)
    public ResponseEntity<ProjectDto> updateProject(@Validated @RequestBody UpdateProjectRq updateProjectRq) {
        return ResponseEntity.ok(new ProjectDto(projectService.update(updateProjectRq)));
    }
}
