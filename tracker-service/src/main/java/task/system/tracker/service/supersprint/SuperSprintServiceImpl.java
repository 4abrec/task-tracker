package task.system.tracker.service.supersprint;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import task.system.tracker.domain.SuperSprint;
import task.system.tracker.dto.supersprint.CreateSuperSprintRq;
import task.system.tracker.dto.supersprint.UpdateSuperSprintRq;
import task.system.tracker.exception.ResourceNotFoundException;
import task.system.tracker.repository.SuperSprintRepository;

@Service
@RequiredArgsConstructor
public class SuperSprintServiceImpl implements SuperSprintService {

    private final SuperSprintRepository superSprintRepository;

    @Override
    public SuperSprint save(CreateSuperSprintRq createSuperSprintRq) {
        SuperSprint superSprint = createSuperSprintRq.toEntity();
        return superSprintRepository.save(superSprint);
    }

    @Override
    public SuperSprint update(UpdateSuperSprintRq updateSuperSprintRq) {
        SuperSprint entitySuperSprintFromDb = getById(updateSuperSprintRq.getId());
        SuperSprint entitySuperSprintUpd = updateSuperSprintRq.toEntity();
        entitySuperSprintUpd.setCreatedAt(entitySuperSprintFromDb.getUpdatedAt());
        return superSprintRepository.save(entitySuperSprintUpd);
    }

    @Override
    public SuperSprint getById(String id) {
        return superSprintRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("SuperSprint with id %s not found", id)));
    }

    @Override
    public Page<SuperSprint> getAll(Integer pageSize, Integer pageNumber) {
        return superSprintRepository.findAll(Pageable.ofSize(pageSize).withPage(pageNumber));
    }

    @Override
    public void deleteById(String id) {
        superSprintRepository.deleteById(id);
    }
}
