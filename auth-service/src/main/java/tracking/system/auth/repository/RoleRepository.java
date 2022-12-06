package tracking.system.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tracking.system.auth.domain.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    @Query(value = "select * from Role role where role.name = :name", nativeQuery = true)
    Optional<Role> findByName(@Param("name") String name);
}
