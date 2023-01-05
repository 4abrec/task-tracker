package task.system.tracker.service.epic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import task.system.tracker.domain.Epic;
import task.system.tracker.domain.Project;
import task.system.tracker.domain.SuperSprint;
import task.system.tracker.dto.epic.CreateEpicRq;
import task.system.tracker.dto.epic.UpdateEpicRq;
import task.system.tracker.exception.ResourceNotFoundException;
import task.system.tracker.repository.EpicRepository;
import task.system.tracker.service.project.ProjectService;
import task.system.tracker.service.supersprint.SuperSprintService;

@Service
@RequiredArgsConstructor
public class EpicServiceImpl implements EpicService {

    private final EpicRepository epicRepository;
    private final ProjectService projectService;
    private final SuperSprintService superSprintService;

    @Override
    public Epic save(CreateEpicRq createEpicRq) {
        Epic epic = createEpicRq.toEntity();
        Project project = projectService.getById(createEpicRq.getProjectId());
        SuperSprint superSprint = superSprintService.getById(createEpicRq.getSuperSprintId());
        epic.setProject(project);
        epic.setSuperSprint(superSprint);
        return epicRepository.save(epic);
    }

    @Override
    public Epic getById(String id) {
        return epicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Epic with id %s not found", id)));
    }

    @Override
    public Epic update(UpdateEpicRq updateEpicRq) {
        Epic entityEpicFromDb = getById(updateEpicRq.getId());
        Epic entityEpicForUpd = updateEpicRq.toEntity();
        entityEpicForUpd.setSuperSprint(superSprintService.getById(updateEpicRq.getSuperSprintId()));
        entityEpicForUpd.setProject(projectService.getById(updateEpicRq.getProjectId()));
        entityEpicForUpd.setCreatedAt(entityEpicFromDb.getCreatedAt());
        return epicRepository.save(entityEpicForUpd);
    }

    @Override
    public Page<Epic> getAll(Integer pageSize, Integer pageNumber) {
        return epicRepository.findAll(Pageable.ofSize(pageSize).withPage(pageNumber));
    }

    @Override
    public void deleteById(String id) {
        epicRepository.deleteById(id);
    }
}
