package task.system.tracker.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import task.system.tracker.domain.Sprint;
import task.system.tracker.dto.page.PageDto;
import task.system.tracker.dto.sprint.CreateSprintRq;
import task.system.tracker.dto.sprint.SprintDto;
import task.system.tracker.dto.sprint.UpdateSprintRq;
import task.system.tracker.service.sprint.SprintService;
import task.system.tracker.util.PageSizeValidator;

@RestController
@RequestMapping(
        value = "/api/sprint"
)
@Api(tags = "Sprint Controller")
@RequiredArgsConstructor
public class RestSprintController {

    private final SprintService sprintService;
    private final PageSizeValidator pageSizeValidator;

    @PostMapping("/save")
    @ApiOperation(value = "Save Sprint", response = SprintDto.class)
    public ResponseEntity<SprintDto> createSprint(@Validated @RequestBody CreateSprintRq createSprintRq) {
        return ResponseEntity.ok(new SprintDto(sprintService.save(createSprintRq)));
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update Sprint", response = SprintDto.class)
    public ResponseEntity<SprintDto> updateSprint(@Validated @RequestBody UpdateSprintRq updateSprintRq) {
        return ResponseEntity.ok(new SprintDto(sprintService.update(updateSprintRq)));
    }

    @GetMapping()
    @ApiOperation(value = "Get SuperSprint by id", response = SprintDto.class)
    public ResponseEntity<SprintDto> getById(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok(new SprintDto(sprintService.getById(id)));
    }


    @GetMapping(value = "/getPage")
    @ApiOperation(value = "Get page with Sprints")
    public ResponseEntity<PageDto<SprintDto>> getPage(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Integer correctPageSize = pageSizeValidator.getPageSize(pageSize);
        System.out.println("mama");
        Page<Sprint> sprintPage = sprintService.getAll(correctPageSize, pageNumber);
        return ResponseEntity.ok(new PageDto<>(SprintDto.toDtoList(sprintPage.getContent()),
                pageNumber, correctPageSize, sprintPage.getTotalPages()));
    }

    @DeleteMapping()
    @ApiOperation(value = "Delete Sprint by id")
    public void deleteSprint(@RequestParam(value = "id") String id) {
        sprintService.deleteById(id);
    }
}
