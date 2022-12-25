package task.system.tracker.service.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import task.system.tracker.domain.Project;
import task.system.tracker.domain.Workload;
import task.system.tracker.dto.project.CreateProjectRq;
import task.system.tracker.dto.project.ProjectDto;
import task.system.tracker.dto.project.UpdateProjectRq;
import task.system.tracker.exception.ResourceNotFoundException;
import task.system.tracker.repository.ProjectRepository;
import task.system.tracker.service.workload.WorkloadService;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final WorkloadService workloadService;

    @Override
    public Project save(CreateProjectRq createWorkloadRq) {
        Project project = createWorkloadRq.toEntity();
        Workload workload = workloadService.getById(createWorkloadRq.getWorkloadId());
        project.setWorkload(workload);
        return projectRepository.save(project);
    }

    @Override
    public Project getById(String id) {
        return projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Project with id %s not found", id)));
    }

    @Override
    public Project update(UpdateProjectRq updateProjectRq) {
        Project entityProjectFromDb = getById(updateProjectRq.getId());
        Project entityProjectUpd = updateProjectRq.toEntity();
        entityProjectUpd.setCreatedAt(entityProjectFromDb.getCreatedAt());
        entityProjectUpd.setWorkload(entityProjectFromDb.getWorkload());
        return projectRepository.save(entityProjectUpd);
    }
}