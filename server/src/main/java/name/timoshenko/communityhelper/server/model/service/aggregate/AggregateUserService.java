package name.timoshenko.communityhelper.server.model.service.aggregate;

import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.User;

import java.util.List;

/**
 *
 */
public interface AggregateUserService {
    Player getActivePlayerByUserLogin(String userLogin);
    List<Player> getPlayersByUserLogin(String userLogin);
    User getUserByPlayerId(Long playerId);
}
