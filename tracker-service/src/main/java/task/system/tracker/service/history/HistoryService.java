package task.system.tracker.service.history;

import org.springframework.data.domain.Page;
import task.system.tracker.domain.History;
import task.system.tracker.dto.history.CreateHistoryRq;
import task.system.tracker.dto.history.UpdateHistoryRq;

public interface HistoryService {

    History save(CreateHistoryRq createHistoryRq);

    History getById(String id);

    History update(UpdateHistoryRq updateHistoryRq);

    Page<History> getAll(Integer pageSize, Integer pageNumber);

    void deleteById(String id);
}
