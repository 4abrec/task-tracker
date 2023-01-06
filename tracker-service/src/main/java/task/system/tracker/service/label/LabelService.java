package task.system.tracker.service.label;

import org.springframework.data.domain.Page;
import task.system.tracker.domain.Label;
import task.system.tracker.dto.label.CreateLabelRq;
import task.system.tracker.dto.label.UpdateLabelRq;

public interface LabelService {

    Label save(CreateLabelRq createLabelRq);

    Label getById(String id);

    Label update(UpdateLabelRq updateLabelRq);

    Page<Label> getAll(Integer pageSize, Integer pageNumber);

    void deleteById(String id);
}
