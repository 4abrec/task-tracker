package task.system.tracker.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import task.system.tracker.domain.Comment;
import task.system.tracker.dto.comment.CommentDto;
import task.system.tracker.dto.comment.CreateCommentRq;
import task.system.tracker.dto.comment.UpdateCommentRq;
import task.system.tracker.dto.page.PageDto;
import task.system.tracker.service.comment.CommentService;
import task.system.tracker.util.PageSizeValidator;

@RestController
@RequestMapping("/api/comment")
@Api(tags = "Comment Controller")
@RequiredArgsConstructor
public class RestCommentController {

    private final PageSizeValidator pageSizeValidator;
    private final CommentService commentService;

    @PostMapping("/save")
    @ApiOperation(value = "Save comment", response = CommentDto.class)
    public ResponseEntity<CommentDto> createComment(@Validated @RequestBody CreateCommentRq createCommentRq) {
        return ResponseEntity.ok(new CommentDto(commentService.save(createCommentRq)));
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update comment", response = CommentDto.class)
    public ResponseEntity<CommentDto> updateComment(@Validated @RequestBody UpdateCommentRq updateCommentRq) {
        return ResponseEntity.ok(new CommentDto(commentService.update(updateCommentRq)));
    }

    @GetMapping
    @ApiOperation(value = "Get comment by id", response = CommentDto.class)
    public ResponseEntity<CommentDto> getComment(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok(new CommentDto(commentService.getById(id)));
    }

    @GetMapping("/getPage")
    @ApiOperation(value = "Get page with comments")
    public ResponseEntity<PageDto<CommentDto>> getPage(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Integer correctPageSize = pageSizeValidator.getPageSize(pageSize);
        Page<Comment> commentPage = commentService.getAll(correctPageSize, pageNumber);
        return ResponseEntity.ok(new PageDto<>(CommentDto.toDtoList(commentPage.getContent()), pageNumber, correctPageSize, commentPage.getTotalPages()));
    }

    @DeleteMapping
    @ApiOperation(value = "Delete comment by id")
    public void deleteBugById(@RequestParam(value = "id") String id) {
        commentService.deleteById(id);
    }
}
