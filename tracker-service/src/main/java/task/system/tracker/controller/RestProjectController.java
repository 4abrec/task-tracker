package task.system.tracker.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import task.system.tracker.domain.Project;
import task.system.tracker.dto.page.PageDto;
import task.system.tracker.dto.project.CreateProjectRq;
import task.system.tracker.dto.project.ProjectDto;
import task.system.tracker.dto.project.UpdateProjectRq;
import task.system.tracker.service.project.ProjectService;
import task.system.tracker.util.PageSizeValidator;

@RestController
@RequestMapping(
        value = "/api/project",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = "Project Controller")
@RequiredArgsConstructor
public class RestProjectController {

    private final ProjectService projectService;
    private final PageSizeValidator pageSizeValidator;

    @PostMapping("/save")
    @ApiOperation(value = "Save project", response = ProjectDto.class)
    public ResponseEntity<ProjectDto> createProject(@Validated @RequestBody CreateProjectRq createProjectRq) {
        return ResponseEntity.ok(new ProjectDto((projectService.save(createProjectRq))));
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update project", response = ProjectDto.class)
    public ResponseEntity<ProjectDto> updateProject(@Validated @RequestBody UpdateProjectRq updateProjectRq) {
        return ResponseEntity.ok(new ProjectDto(projectService.update(updateProjectRq)));
    }

    @GetMapping
    @ApiOperation(value = "Get project by id", response = ProjectDto.class)
    public ResponseEntity<ProjectDto> getProject(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok(new ProjectDto(projectService.getById(id)));
    }

    @GetMapping("/getPage")
    @ApiOperation(value = "Get page with projects")
    public ResponseEntity<PageDto<ProjectDto>> getPage (
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Integer correctPageSize = pageSizeValidator.getPageSize(pageSize);
        Page<Project> projectsPage = projectService.getAll(correctPageSize, pageNumber);
        return ResponseEntity.ok(new PageDto<>(ProjectDto.toDtoList(projectsPage.getContent()),
                pageNumber, correctPageSize, projectsPage.getTotalPages()));
    }

    @DeleteMapping
    @ApiOperation(value = "Delete project by id")
    public void deleteProjectById(@RequestParam(value = "id") String id) {
        projectService.deleteById(id);
    }
}
