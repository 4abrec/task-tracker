package task.system.tracker.service.workload;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import task.system.tracker.domain.Workload;
import task.system.tracker.dto.workload.CreateWorkloadRq;
import task.system.tracker.dto.workload.UpdateWorkloadRq;
import task.system.tracker.exception.ResourceNotFoundException;
import task.system.tracker.repository.WorkloadRepository;

@Service
@RequiredArgsConstructor
public class WorkloadServiceImpl implements WorkloadService {

    private final WorkloadRepository workloadRepository;

    @Override
    public Workload getById(String id) {
        return workloadRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Workload with id %s not found", id)));
    }

    @Override
    public Workload save(CreateWorkloadRq createWorkloadRq) {
        Workload workload = createWorkloadRq.toEntity();
        return workloadRepository.save(workload);
    }


    @Override
    public Workload update(UpdateWorkloadRq updateWorkloadRq) {
        Workload entityWorkloadFromDB = getById(updateWorkloadRq.getId());
        Workload entityWorkloadUpd = updateWorkloadRq.toEntity();
        entityWorkloadUpd.setCreatedAt(entityWorkloadFromDB.getCreatedAt());
        return workloadRepository.save(entityWorkloadUpd);
    }

    @Override
    public void deleteById(String id) {
        workloadRepository.deleteById(id);
    }

    @Override
    public Page<Workload> getAll(Integer pageSize, Integer pageNumber) {
        return workloadRepository.findAll(Pageable
                .ofSize(pageSize)
                .withPage(pageNumber));
    }
}
