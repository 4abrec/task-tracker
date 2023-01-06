package task.system.tracker.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import task.system.tracker.domain.Comment;
import task.system.tracker.domain.Label;
import task.system.tracker.dto.comment.CommentDto;
import task.system.tracker.dto.comment.CreateCommentRq;
import task.system.tracker.dto.comment.UpdateCommentRq;
import task.system.tracker.dto.label.CreateLabelRq;
import task.system.tracker.dto.label.LabelDto;
import task.system.tracker.dto.label.UpdateLabelRq;
import task.system.tracker.dto.page.PageDto;
import task.system.tracker.service.comment.CommentService;
import task.system.tracker.service.label.LabelService;
import task.system.tracker.util.PageSizeValidator;

@RestController
@RequestMapping("/api/label")
@Api(tags = "Label Controller")
@RequiredArgsConstructor
public class RestLabelController {

    private final PageSizeValidator pageSizeValidator;
    private final LabelService labelService;

    @PostMapping("/save")
    @ApiOperation(value = "Save label", response = LabelDto.class)
    public ResponseEntity<LabelDto> createLabel(@Validated @RequestBody CreateLabelRq createLabelRq) {
        return ResponseEntity.ok(new LabelDto(labelService.save(createLabelRq)));
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update label", response = LabelDto.class)
    public ResponseEntity<LabelDto> updateLabel(@Validated @RequestBody UpdateLabelRq updateLabelRq) {
        return ResponseEntity.ok(new LabelDto(labelService.update(updateLabelRq)));
    }

    @GetMapping
    @ApiOperation(value = "Get label by id", response = LabelDto.class)
    public ResponseEntity<LabelDto> getLabel(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok(new LabelDto(labelService.getById(id)));
    }

    @GetMapping("/getPage")
    @ApiOperation(value = "Get page with labels")
    public ResponseEntity<PageDto<LabelDto>> getPage(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Integer correctPageSize = pageSizeValidator.getPageSize(pageSize);
        Page<Label> labelPage = labelService.getAll(correctPageSize, pageNumber);
        return ResponseEntity.ok(new PageDto<>(LabelDto.toDtoList(labelPage.getContent()), pageNumber, correctPageSize, labelPage.getTotalPages()));
    }

    @DeleteMapping
    @ApiOperation(value = "Delete label by id")
    public void deleteLabelById(@RequestParam(value = "id") String id) {
        labelService.deleteById(id);
    }
}
