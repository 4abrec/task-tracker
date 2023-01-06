package task.system.tracker.service.label;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import task.system.tracker.domain.Label;
import task.system.tracker.domain.Task;
import task.system.tracker.dto.label.CreateLabelRq;
import task.system.tracker.dto.label.UpdateLabelRq;
import task.system.tracker.exception.ResourceNotFoundException;
import task.system.tracker.repository.LabelRepository;
import task.system.tracker.service.task.TaskService;

@Service
@RequiredArgsConstructor
public class LabelServiceImpl implements LabelService {

    private final TaskService taskService;
    private final LabelRepository labelRepository;

    @Override
    public Label save(CreateLabelRq createLabelRq) {
        Task task = taskService.getById(createLabelRq.getAuthorId());
        Label label = createLabelRq.toEntity();
        label.setTask(task);
        return labelRepository.save(label);
    }

    @Override
    public Label getById(String id) {
        return labelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Label with id %s not found", id)));
    }

    @Override
    public Label update(UpdateLabelRq updateLabelRq) {
        Label entityLabelFromDb = getById(updateLabelRq.getId());
        Label entityLabelForUpd = updateLabelRq.toEntity();
        entityLabelForUpd.setTask(taskService.getById(updateLabelRq.getTaskId()));
        entityLabelForUpd.setCreatedAt(entityLabelFromDb.getCreatedAt());
        return labelRepository.save(entityLabelForUpd);
    }

    @Override
    public Page<Label> getAll(Integer pageSize, Integer pageNumber) {
        return labelRepository.findAll(Pageable.ofSize(pageSize).withPage(pageNumber));
    }

    @Override
    public void deleteById(String id) {
        labelRepository.deleteById(id);
    }
}
