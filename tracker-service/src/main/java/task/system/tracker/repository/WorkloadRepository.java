package task.system.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.system.tracker.domain.Workload;

@Repository
public interface WorkloadRepository extends JpaRepository<Workload, String> {


}
