package task.system.tracker.service.supersprint;

import org.springframework.data.domain.Page;
import task.system.tracker.domain.SuperSprint;
import task.system.tracker.dto.supersprint.CreateSuperSprintRq;
import task.system.tracker.dto.supersprint.UpdateSuperSprintRq;

public interface SuperSprintService {

    SuperSprint save(CreateSuperSprintRq createSuperSprintRq);

    SuperSprint update(UpdateSuperSprintRq updateSuperSprintRq);

    SuperSprint getById(String id);

    Page<SuperSprint> getAll(Integer pageSize, Integer pageNumber);

    void deleteById(String id);
}
