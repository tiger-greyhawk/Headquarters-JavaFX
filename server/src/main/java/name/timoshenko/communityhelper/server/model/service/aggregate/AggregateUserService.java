package name.timoshenko.communityhelper.server.model.service.aggregate;

import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.User;

import java.util.List;

/**
 *
 */
public interface AggregateUserService {

    Player getActivePlayerByUserId(Long userId);
    List<Player> getPlayersByUserId(Long userId);
    User getUserByPlayerId(Long playerId);
}
