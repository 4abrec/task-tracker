package task.system.tracker.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import task.system.tracker.domain.SuperSprint;
import task.system.tracker.dto.page.PageDto;
import task.system.tracker.dto.supersprint.CreateSuperSprintRq;
import task.system.tracker.dto.supersprint.SuperSprintDto;
import task.system.tracker.dto.supersprint.UpdateSuperSprintRq;
import task.system.tracker.service.supersprint.SuperSprintService;
import task.system.tracker.util.PageSizeValidator;

@RestController
@RequestMapping("/api/superSprint")
@Api(tags = "SuperSprintController Controller")
@RequiredArgsConstructor
public class RestSuperSprintController {

    private final SuperSprintService superSprintService;
    private final PageSizeValidator pageSizeValidator;

    @PostMapping("/save")
    @ApiOperation(value = "Save SuperSprint", response = SuperSprintDto.class)
    public ResponseEntity<SuperSprintDto> createSuperSprint(@Validated @RequestBody CreateSuperSprintRq createSuperSprintRq) {
        return ResponseEntity.ok(new SuperSprintDto(superSprintService.save(createSuperSprintRq)));
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update SuperSprint", response = SuperSprintDto.class)
    public ResponseEntity<SuperSprintDto> updateSuperSprint(@Validated @RequestBody UpdateSuperSprintRq updateSuperSprintRq) {
        return ResponseEntity.ok(new SuperSprintDto(superSprintService.update(updateSuperSprintRq)));
    }

    @GetMapping()
    @ApiOperation(value = "Get SuperSprint by id", response = SuperSprintDto.class)
    public ResponseEntity<SuperSprintDto> getById(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok(new SuperSprintDto(superSprintService.getById(id)));
    }


    @GetMapping("/getPage")
    @ApiOperation(value = "Get page with SuperSprints")
    public ResponseEntity<PageDto<SuperSprintDto>> getPage(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Integer correctPageSize = pageSizeValidator.getPageSize(pageSize);
        Page<SuperSprint> superSprintPage = superSprintService.getAll(correctPageSize, pageNumber);
        return ResponseEntity.ok(new PageDto<>(SuperSprintDto.toDtoList(superSprintPage.getContent()),
                pageNumber, correctPageSize, superSprintPage.getTotalPages()));
    }

    @DeleteMapping()
    @ApiOperation(value = "Delete SuperSprint by id")
    public void deleteSuperSprint(@RequestParam(value = "id") String id) {
        superSprintService.deleteById(id);
    }
}
