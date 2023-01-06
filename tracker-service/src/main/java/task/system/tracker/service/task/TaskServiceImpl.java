package task.system.tracker.service.task;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import task.system.tracker.domain.History;
import task.system.tracker.domain.Task;
import task.system.tracker.dto.task.CreateTaskRq;
import task.system.tracker.dto.task.UpdateTaskRq;
import task.system.tracker.exception.ResourceNotFoundException;
import task.system.tracker.repository.TaskRepository;
import task.system.tracker.service.history.HistoryService;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final HistoryService historyService;

    @Override
    public Task save(CreateTaskRq createTaskRq) {
        History history = historyService.getById(createTaskRq.getHistoryId());
        Task task = createTaskRq.toEntity();
        task.setHistory(history);
        return taskRepository.save(task);
    }

    @Override
    public Task getById(String id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Task with id %s not found", id)));
    }

    @Override
    public Task update(UpdateTaskRq updateTaskRq) {
        Task entityTaskFromDb = getById(updateTaskRq.getId());
        Task entityTaskForUpd = updateTaskRq.toEntity();
        entityTaskForUpd.setHistory(historyService.getById(updateTaskRq.getHistoryId()));
        entityTaskForUpd.setCreatedAt(entityTaskFromDb.getCreatedAt());
        return taskRepository.save(entityTaskForUpd);
    }

    @Override
    public Page<Task> getAll(Integer pageSize, Integer pageNumber) {
        return taskRepository.findAll(Pageable.ofSize(pageSize).withPage(pageNumber));
    }

    @Override
    public void deleteById(String id) {
        taskRepository.deleteById(id);
    }
}
