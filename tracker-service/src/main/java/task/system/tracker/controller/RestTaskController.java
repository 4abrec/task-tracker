package task.system.tracker.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import task.system.tracker.domain.Bug;
import task.system.tracker.domain.Task;
import task.system.tracker.dto.bug.BugDto;
import task.system.tracker.dto.bug.CreateBugRq;
import task.system.tracker.dto.bug.UpdateBugRq;
import task.system.tracker.dto.page.PageDto;
import task.system.tracker.dto.task.CreateTaskRq;
import task.system.tracker.dto.task.TaskDto;
import task.system.tracker.dto.task.UpdateTaskRq;
import task.system.tracker.service.bug.BugService;
import task.system.tracker.service.task.TaskService;
import task.system.tracker.util.PageSizeValidator;

@RestController
@RequestMapping("/api/task")
@Api(tags = "TaskController")
@RequiredArgsConstructor
public class RestTaskController {

    private final PageSizeValidator pageSizeValidator;
    private final TaskService taskService;

    @PostMapping("/save")
    @ApiOperation(value = "Save task", response = TaskDto.class)
    public ResponseEntity<TaskDto> createTask(@Validated @RequestBody CreateTaskRq createTaskRq) {
        return ResponseEntity.ok(new TaskDto(taskService.save(createTaskRq)));
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update task", response = TaskDto.class)
    public ResponseEntity<TaskDto> updateTask(@Validated @RequestBody UpdateTaskRq updateTaskRq) {
        return ResponseEntity.ok(new TaskDto(taskService.update(updateTaskRq)));
    }

    @GetMapping
    @ApiOperation(value = "Get task by id", response = TaskDto.class)
    public ResponseEntity<TaskDto> getTask(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok(new TaskDto(taskService.getById(id)));
    }

    @GetMapping("/getPage")
    @ApiOperation(value = "Get page with bugs")
    public ResponseEntity<PageDto<TaskDto>> getPage(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Integer correctPageSize = pageSizeValidator.getPageSize(pageSize);
        Page<Task> taskPage = taskService.getAll(correctPageSize, pageNumber);
        return ResponseEntity.ok(new PageDto<>(TaskDto.toDtoList(taskPage.getContent()),
                pageNumber, correctPageSize, taskPage.getTotalPages()));
    }

    @DeleteMapping
    @ApiOperation(value = "Delete task by id")
    public void deleteTaskById(@RequestParam(value = "id") String id) {
        taskService.deleteById(id);
    }
}
