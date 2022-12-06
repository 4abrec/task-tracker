package task.system.tracker.service;

import task.system.tracker.domain.Workload;
import task.system.tracker.dto.CreateWorkloadRq;

public interface WorkloadService {
    Workload save(CreateWorkloadRq createWorkloadRq);
}
