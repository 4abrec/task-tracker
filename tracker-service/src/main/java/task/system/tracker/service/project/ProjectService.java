package task.system.tracker.service.project;

import task.system.tracker.domain.Project;
import task.system.tracker.dto.project.CreateProjectRq;
import task.system.tracker.dto.project.ProjectDto;
import task.system.tracker.dto.project.UpdateProjectRq;

public interface ProjectService {

    Project save(CreateProjectRq createWorkloadRq);

    Project getById(String id);

    Project update(UpdateProjectRq updateProjectRq);


}
