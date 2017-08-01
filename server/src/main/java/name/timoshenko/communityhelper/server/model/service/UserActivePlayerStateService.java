package name.timoshenko.communityhelper.server.model.service;

import name.timoshenko.communityhelper.server.model.domain.UserActivePlayer;

/**
 *
 */
public interface UserActivePlayerStateService {
    UserActivePlayer getActivePlayer(Long userId);
    UserActivePlayer setActivePlayer(UserActivePlayer userActivePlayer);
}
