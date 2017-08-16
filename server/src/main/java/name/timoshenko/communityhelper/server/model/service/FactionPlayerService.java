package name.timoshenko.communityhelper.server.model.service;

import java.util.List;

/**
 *
 */
public interface FactionPlayerService {
    List<Long> findPlayersByFactionId(Long factionId);
    Long findFactionByPlayerId(Long playerId);
}
