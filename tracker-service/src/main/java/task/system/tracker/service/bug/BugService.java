package task.system.tracker.service.bug;

import org.springframework.data.domain.Page;
import task.system.tracker.domain.Bug;
import task.system.tracker.dto.bug.CreateBugRq;
import task.system.tracker.dto.bug.UpdateBugRq;

public interface BugService {

    Bug save(CreateBugRq createBugRq);

    Bug getById(String id);

    Bug update(UpdateBugRq updateBugRq);

    Page<Bug> getAll(Integer pageSize, Integer pageNumber);

    void deleteById(String id);
}
