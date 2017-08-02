package name.timoshenko.communityhelper.server.model.service;

import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.UserActivePlayer;

/**
 *
 */
public interface UserActivePlayerService {
    UserActivePlayer getUserActivePlayer(Long userId);
    Player getActivePlayer(Long userId);
    UserActivePlayer setActivePlayer(UserActivePlayer userActivePlayer);
}
