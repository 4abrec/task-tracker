package task.system.tracker.service.workload;

import org.springframework.data.domain.Page;
import task.system.tracker.domain.Workload;
import task.system.tracker.dto.workload.CreateWorkloadRq;
import task.system.tracker.dto.workload.UpdateWorkloadRq;

public interface WorkloadService {
    Workload save(CreateWorkloadRq createWorkloadRq);

    Workload update(UpdateWorkloadRq updateWorkloadRq);

    Workload getById(String id);

    Page<Workload> getAll(Integer pageSize, Integer pageNumber);

    void deleteById(String id);
}
