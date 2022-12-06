package task.system.tracker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import task.system.tracker.domain.Workload;
import task.system.tracker.dto.CreateWorkloadRq;
import task.system.tracker.repository.WorkloadRepository;
import task.system.tracker.service.WorkloadService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WorkloadServiceImpl implements WorkloadService {

    private final WorkloadRepository workloadRepository;

    @Override
    public Workload save(CreateWorkloadRq createWorkloadRq) {
        Workload workload = createWorkloadRq.toEntity();
        workload.setCreatedAt(LocalDateTime.now());
        workload.setUpdatedAt(LocalDateTime.now());
        return workloadRepository.save(workload);
    }
}
