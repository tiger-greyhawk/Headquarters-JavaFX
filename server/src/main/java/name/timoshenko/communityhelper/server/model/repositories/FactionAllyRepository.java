package name.timoshenko.communityhelper.server.model.repositories;

import name.timoshenko.communityhelper.server.model.domain.FactionAlly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactionAllyRepository extends JpaRepository<FactionAlly, Long> {
    List<FactionAlly> findByFirstFactionId(Long firstFactionId);
    List<FactionAlly> findBySecondFactionId(Long secondFactionId);
    FactionAlly findByFirstFactionIdAndSecondFactionId(Long firstId, Long secondId);
}
