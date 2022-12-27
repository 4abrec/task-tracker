package task.system.tracker.service.epic;

import org.springframework.data.domain.Page;
import task.system.tracker.domain.Epic;
import task.system.tracker.dto.epic.CreateEpicRq;
import task.system.tracker.dto.epic.UpdateEpicRq;

public interface EpicService {

    Epic save(CreateEpicRq createEpicRq);

    Epic getById(String id);

    Epic update(UpdateEpicRq updateEpicRq);

    Page<Epic> getAll(Integer pageSize, Integer pageNumber);

    void deleteById(String id);
}
