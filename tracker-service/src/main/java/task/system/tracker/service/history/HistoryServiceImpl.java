package task.system.tracker.service.history;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import task.system.tracker.domain.Epic;
import task.system.tracker.domain.History;
import task.system.tracker.domain.Sprint;
import task.system.tracker.dto.history.CreateHistoryRq;
import task.system.tracker.dto.history.UpdateHistoryRq;
import task.system.tracker.exception.ResourceNotFoundException;
import task.system.tracker.repository.HistoryRepository;
import task.system.tracker.service.epic.EpicService;
import task.system.tracker.service.sprint.SprintService;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final EpicService epicService;
    private final SprintService sprintService;
    private final HistoryRepository historyRepository;

    @Override
    public History save(CreateHistoryRq createHistoryRq) {
        History history = createHistoryRq.toEntity();
        Epic epic = epicService.getById(createHistoryRq.getEpicId());
        Sprint sprint = sprintService.getById(createHistoryRq.getSprintId());
        history.setEpic(epic);
        history.setSprint(sprint);
        return historyRepository.save(history);
    }

    @Override
    public History getById(String id) {
        return historyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("History with id %s not found", id)));
    }

    @Override
    public History update(UpdateHistoryRq updateHistoryRq) {
        History entityHistoryFromDb = getById(updateHistoryRq.getId());
        History entityHistoryForUpd = updateHistoryRq.toEntity();
        entityHistoryForUpd.setSprint(sprintService.getById(updateHistoryRq.getSprintId()));
        entityHistoryForUpd.setEpic(epicService.getById(updateHistoryRq.getEpicId()));
        entityHistoryForUpd.setCreatedAt(entityHistoryFromDb.getCreatedAt());
        return historyRepository.save(entityHistoryForUpd);
    }

    @Override
    public Page<History> getAll(Integer pageSize, Integer pageNumber) {
        return historyRepository.findAll(Pageable.ofSize(pageSize).withPage(pageNumber));
    }

    @Override
    public void deleteById(String id) {
        historyRepository.deleteById(id);
    }
}
