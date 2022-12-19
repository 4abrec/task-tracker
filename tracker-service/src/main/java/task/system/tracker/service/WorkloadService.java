package task.system.tracker.service;

import task.system.tracker.domain.Workload;
import task.system.tracker.dto.workload.CreateWorkloadRq;
import task.system.tracker.dto.workload.UpdateWorkloadRq;

public interface WorkloadService {
    Workload save(CreateWorkloadRq createWorkloadRq);

    Workload update(UpdateWorkloadRq updateWorkloadRq);

    Workload findById(String id);

    void deleteById(String id);
}
