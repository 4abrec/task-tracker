package task.system.tracker.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import task.system.tracker.domain.Bug;
import task.system.tracker.dto.bug.BugDto;
import task.system.tracker.dto.bug.CreateBugRq;
import task.system.tracker.dto.bug.UpdateBugRq;
import task.system.tracker.dto.page.PageDto;
import task.system.tracker.service.bug.BugService;
import task.system.tracker.util.PageSizeValidator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bug")
@Api(tags = "Bug Controller")
public class RestBugController {

    private final PageSizeValidator pageSizeValidator;
    private final BugService bugService;

    @PostMapping("/save")
    @ApiOperation(value = "Save bug", response = BugDto.class)
    public ResponseEntity<BugDto> createBug(@Validated @RequestBody CreateBugRq createBugRq) {
        return ResponseEntity.ok(new BugDto(bugService.save(createBugRq)));
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update bug", response = BugDto.class)
    public ResponseEntity<BugDto> updateBug(@Validated @RequestBody UpdateBugRq updateBugRq) {
        return ResponseEntity.ok(new BugDto(bugService.update(updateBugRq)));
    }

    @GetMapping
    @ApiOperation(value = "Get bug by id", response = BugDto.class)
    public ResponseEntity<BugDto> getBug(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok(new BugDto(bugService.getById(id)));
    }

    @GetMapping("/getPage")
    @ApiOperation(value = "Get page with bugs")
    public ResponseEntity<PageDto<BugDto>> getPage(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Integer correctPageSize = pageSizeValidator.getPageSize(pageSize);
        Page<Bug> bugPage = bugService.getAll(correctPageSize, pageNumber);
        return ResponseEntity.ok(new PageDto<>(BugDto.toDtoList(bugPage.getContent()),
                pageNumber, correctPageSize, bugPage.getTotalPages()));
    }

    @DeleteMapping
    @ApiOperation(value = "Delete bug by id")
    public void deleteEpicById(@RequestParam(value = "id") String id) {
        bugService.deleteById(id);
    }
}
