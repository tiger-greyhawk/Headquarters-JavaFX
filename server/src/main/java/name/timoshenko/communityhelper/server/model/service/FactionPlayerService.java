package name.timoshenko.communityhelper.server.model.service;

import name.timoshenko.communityhelper.server.model.domain.Faction;
import name.timoshenko.communityhelper.server.model.domain.FactionPlayer;
import name.timoshenko.communityhelper.server.model.domain.Player;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface FactionPlayerService {
    List<Long> findPlayerIdsByFactionId(Long factionId);
    List<FactionPlayer> findFactionPlayersByFactionId(Long factionId);
    Optional<Long> findFactionByPlayerId(Long playerId);
    FactionPlayer addPlayerToFaction(Player player, Faction faction, Boolean invited, Boolean confirmed);
    Boolean deletePlayerFromFaction(Long factionId, Long playerId);
    Boolean deletePlayersFromFaction(Long factionId);
}
