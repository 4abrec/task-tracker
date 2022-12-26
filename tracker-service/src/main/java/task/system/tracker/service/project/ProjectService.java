package task.system.tracker.service.project;

import org.springframework.data.domain.Page;
import task.system.tracker.domain.Project;
import task.system.tracker.dto.project.CreateProjectRq;
import task.system.tracker.dto.project.UpdateProjectRq;

public interface ProjectService {

    Project save(CreateProjectRq createWorkloadRq);

    Project getById(String id);

    Project update(UpdateProjectRq updateProjectRq);

    Page<Project> getAll(Integer pageSize, Integer pageNumber);

    void deleteById(String id);


}
