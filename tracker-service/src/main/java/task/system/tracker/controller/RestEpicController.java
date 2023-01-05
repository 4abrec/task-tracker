package task.system.tracker.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import task.system.tracker.domain.Epic;
import task.system.tracker.dto.epic.CreateEpicRq;
import task.system.tracker.dto.epic.EpicDto;
import task.system.tracker.dto.epic.UpdateEpicRq;
import task.system.tracker.dto.page.PageDto;
import task.system.tracker.service.epic.EpicService;
import task.system.tracker.util.PageSizeValidator;

@RestController
@RequestMapping( "/api/epic")
@Api(tags = "Epic Controller")
@RequiredArgsConstructor
public class RestEpicController {

    private final EpicService epicService;
    private final PageSizeValidator pageSizeValidator;

    @PostMapping("/save")
    @ApiOperation(value = "Save epic", response = EpicDto.class)
    public ResponseEntity<EpicDto> createEpic(@Validated @RequestBody  CreateEpicRq createEpicRq) {
        return ResponseEntity.ok(new EpicDto(epicService.save(createEpicRq)));
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update epic", response = EpicDto.class)
    public ResponseEntity<EpicDto> updateEpic(@Validated @RequestBody UpdateEpicRq updateEpicRq) {
        return ResponseEntity.ok(new EpicDto(epicService.update(updateEpicRq)));
    }

    @GetMapping
    @ApiOperation(value = "Get epic by id", response = EpicDto.class)
    public ResponseEntity<EpicDto> getEpic(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok(new EpicDto(epicService.getById(id)));
    }

    @GetMapping("/getPage")
    @ApiOperation(value = "Get page with epics")
    public ResponseEntity<PageDto<EpicDto>> getPage (
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Integer correctPageSize = pageSizeValidator.getPageSize(pageSize);
        Page<Epic> epicsPage = epicService.getAll(correctPageSize, pageNumber);
        return ResponseEntity.ok(new PageDto<>(EpicDto.toDtoList(epicsPage.getContent()),
                pageNumber, correctPageSize, epicsPage.getTotalPages()));
    }

    @DeleteMapping
    @ApiOperation(value = "Delete epic by id")
    public void deleteEpicById(@RequestParam(value = "id") String id) {
        epicService.deleteById(id);
    }
}
