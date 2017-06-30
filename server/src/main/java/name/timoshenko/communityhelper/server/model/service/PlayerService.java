package name.timoshenko.communityhelper.server.model.service;

import name.timoshenko.communityhelper.server.model.domain.Player;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface PlayerService {
    Optional<Player> findPlayer(long id);
    List<Player> findPlayersByUserId(long userId);
    List<Player> getPlayers(List<Long> id);
}
