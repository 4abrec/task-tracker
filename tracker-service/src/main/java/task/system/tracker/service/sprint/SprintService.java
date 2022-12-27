package task.system.tracker.service.sprint;

import org.springframework.data.domain.Page;
import task.system.tracker.domain.Sprint;
import task.system.tracker.dto.sprint.CreateSprintRq;
import task.system.tracker.dto.sprint.UpdateSprintRq;

public interface SprintService {

    Sprint save(CreateSprintRq createSuperSprintRq);

    Sprint update(UpdateSprintRq updateSuperSprintRq);

    Sprint getById(String id);

    Page<Sprint> getAll(Integer pageSize, Integer pageNumber);

    void deleteById(String id);
}
