package name.timoshenko.communityhelper.server.model.repositories;

import name.timoshenko.communityhelper.server.model.domain.FactionPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Repository
public interface FactionPlayerRepository extends JpaRepository<FactionPlayer, Long> {
    List<FactionPlayer> findByFactionId(Long factionId);
    Optional<FactionPlayer> findByPlayerId(Long playerId);
}
