package task.system.tracker.service.bug;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import task.system.tracker.domain.Bug;
import task.system.tracker.domain.History;
import task.system.tracker.dto.bug.CreateBugRq;
import task.system.tracker.dto.bug.UpdateBugRq;
import task.system.tracker.exception.ResourceNotFoundException;
import task.system.tracker.repository.BugRepository;
import task.system.tracker.service.history.HistoryService;

@Service
@RequiredArgsConstructor
public class BugServiceImpl implements BugService {

    private final HistoryService historyService;
    private final BugRepository bugRepository;

    @Override
    public Bug save(CreateBugRq createBugRq) {
        History history = historyService.getById(createBugRq.getHistoryId());
        Bug bug = createBugRq.toEntity();
        bug.setHistory(history);
        return bugRepository.save(bug);

    }

    @Override
    public Bug getById(String id) {
        return bugRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Bug with id %s not found", id)));
    }

    @Override
    public Bug update(UpdateBugRq updateBugRq) {
        Bug entityBugFromDb = getById(updateBugRq.getId());
        Bug entityBugForUpd = updateBugRq.toEntity();
        History history = historyService.getById(updateBugRq.getHistoryId());
        entityBugForUpd.setHistory(history);
        entityBugForUpd.setCreatedAt(entityBugFromDb.getCreatedAt());
        return bugRepository.save(entityBugForUpd);
    }

    @Override
    public Page<Bug> getAll(Integer pageSize, Integer pageNumber) {
        return bugRepository.findAll(Pageable.ofSize(pageSize).withPage(pageNumber));
    }

    @Override
    public void deleteById(String id) {
        bugRepository.deleteById(id);
    }
}
