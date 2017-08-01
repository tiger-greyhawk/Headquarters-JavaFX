package name.timoshenko.communityhelper.server.model.service;

import name.timoshenko.communityhelper.server.model.domain.UserActivePlayer;

/**
 *
 */
public interface UserActivePlayerService {
    UserActivePlayer getActivePlayer(Long userId);
    UserActivePlayer setActivePlayer(UserActivePlayer userActivePlayer);
}
