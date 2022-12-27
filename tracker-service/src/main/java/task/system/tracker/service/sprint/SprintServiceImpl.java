package task.system.tracker.service.sprint;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import task.system.tracker.domain.Sprint;
import task.system.tracker.domain.SuperSprint;
import task.system.tracker.dto.sprint.CreateSprintRq;
import task.system.tracker.dto.sprint.UpdateSprintRq;
import task.system.tracker.exception.ResourceNotFoundException;
import task.system.tracker.repository.SprintRepository;
import task.system.tracker.service.supersprint.SuperSprintService;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService{

    private final SprintRepository sprintRepository;
    private final SuperSprintService superSprintService;

    @Override
    public Sprint save(CreateSprintRq createSuperSprintRq) {
        SuperSprint superSprint = superSprintService.getById(createSuperSprintRq.getSuperSprintId());
        Sprint sprint = createSuperSprintRq.toEntity();
        sprint.setSuperSprint(superSprint);
        return sprintRepository.save(sprint);
    }

    @Override
    public Sprint update(UpdateSprintRq updateSuperSprintRq) {
        Sprint entitySprintFromDb = getById(updateSuperSprintRq.getId());
        Sprint entitySprintForUpd = updateSuperSprintRq.toEntity();
        entitySprintForUpd.setCreatedAt(entitySprintFromDb.getCreatedAt());
        entitySprintForUpd.setSuperSprint(entitySprintFromDb.getSuperSprint());
        return sprintRepository.save(entitySprintForUpd);
    }

    @Override
    public Sprint getById(String id) {
        return sprintRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Sprint with id %s not found", id)));
    }

    @Override
    public Page<Sprint> getAll(Integer pageSize, Integer pageNumber) {
        return sprintRepository.findAll(Pageable.ofSize(pageSize).withPage(pageNumber));
    }

    @Override
    public void deleteById(String id) {
        sprintRepository.deleteById(id);
    }
}
