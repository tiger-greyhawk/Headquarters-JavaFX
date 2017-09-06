package name.timoshenko.communityhelper.server.model.repositories;

import name.timoshenko.communityhelper.server.model.domain.RequestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestResourceRepository extends JpaRepository<RequestResource, Long> {
    List<RequestResource> findRequestResourceByPlayerId(Long playerId);
}
