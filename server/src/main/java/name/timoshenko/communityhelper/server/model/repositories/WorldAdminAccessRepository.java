package name.timoshenko.communityhelper.server.model.repositories;

import name.timoshenko.communityhelper.server.model.domain.WorldAdminAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorldAdminAccessRepository extends JpaRepository<WorldAdminAccess, Long>{
}
