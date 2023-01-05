package task.system.tracker.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import task.system.tracker.domain.History;
import task.system.tracker.dto.history.CreateHistoryRq;
import task.system.tracker.dto.history.HistoryDto;
import task.system.tracker.dto.history.UpdateHistoryRq;
import task.system.tracker.dto.page.PageDto;
import task.system.tracker.service.history.HistoryService;
import task.system.tracker.util.PageSizeValidator;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/history")
@Api(tags = "History Controller")
public class RestHistoryController {

    private final PageSizeValidator pageSizeValidator;
    private final HistoryService historyService;

    @PostMapping("/save")
    @ApiOperation(value = "Save history", response = HistoryDto.class)
    public ResponseEntity<HistoryDto> createHistory(@Validated @RequestBody CreateHistoryRq createHistoryRq) {
        return ResponseEntity.ok(new HistoryDto(historyService.save(createHistoryRq)));
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update history", response = HistoryDto.class)
    public ResponseEntity<HistoryDto> updateHistory(@Validated @RequestBody UpdateHistoryRq updateHistoryRq) {
        return ResponseEntity.ok(new HistoryDto(historyService.update(updateHistoryRq)));
    }

    @GetMapping
    @ApiOperation(value = "Get history by id", response = HistoryDto.class)
    public ResponseEntity<HistoryDto> getHistory(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok(new HistoryDto(historyService.getById(id)));
    }

    @GetMapping("/getPage")
    @ApiOperation(value = "Get page with histories")
    public ResponseEntity<PageDto<HistoryDto>> getPage (
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Integer correctPageSize = pageSizeValidator.getPageSize(pageSize);
        Page<History> historyPage = historyService.getAll(correctPageSize, pageNumber);
        return ResponseEntity.ok(new PageDto<>(HistoryDto.toDtoList(historyPage.getContent()),
                pageNumber, correctPageSize, historyPage.getTotalPages()));
    }

    @DeleteMapping
    @ApiOperation(value = "Delete history by id")
    public void deleteEpicById(@RequestParam(value = "id") String id) {
        historyService.deleteById(id);
    }
}
