package task.system.tracker.service.task;

import org.springframework.data.domain.Page;
import task.system.tracker.domain.Task;
import task.system.tracker.dto.task.CreateTaskRq;
import task.system.tracker.dto.task.UpdateTaskRq;

public interface TaskService {

    Task save(CreateTaskRq createTaskRq);

    Task getById(String id);

    Task update(UpdateTaskRq updateTaskRq);

    Page<Task> getAll(Integer pageSize, Integer pageNumber);

    void deleteById(String id);
}
