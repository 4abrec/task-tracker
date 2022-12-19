package task.system.tracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import task.system.tracker.domain.Workload;
import task.system.tracker.dto.workload.CreateWorkloadRq;
import task.system.tracker.dto.workload.UpdateWorkloadRq;
import task.system.tracker.exception.ResourceNotFoundException;
import task.system.tracker.repository.WorkloadRepository;
import task.system.tracker.service.WorkloadService;

@Service
@RequiredArgsConstructor
public class WorkloadServiceImpl implements WorkloadService {

    private final WorkloadRepository workloadRepository;

    @Override
    public Workload findById(String id) {
        return workloadRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Workload with id %s not found", id)));
    }

    @Override
    public Workload save(CreateWorkloadRq createWorkloadRq) {
        Workload workload = createWorkloadRq.toEntity();
        return workloadRepository.save(workload);
    }


    @Override
    public Workload update(UpdateWorkloadRq updateWorkloadRq) {
        Workload entityWorkloadFromDB = findById(updateWorkloadRq.getId());
        Workload entityWorkloadUpd = updateWorkloadRq.toEntity();
        entityWorkloadUpd.setCreatedAt(entityWorkloadFromDB.getCreatedAt());
        return workloadRepository.save(entityWorkloadUpd);
    }

    @Override
    public void deleteById(String id) {
        workloadRepository.deleteById(id);
    }
}
