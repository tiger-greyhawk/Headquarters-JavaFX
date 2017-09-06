package name.timoshenko.communityhelper.server.model.service;

import name.timoshenko.communityhelper.server.model.domain.Player;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface PlayerService {
    Optional<Player> findPlayer(Long id);
    List<Player> findPlayersByUserId(Long userId);
    List<Player> getPlayers(List<Long> ids);
    Player addPlayer(Player player);
}
