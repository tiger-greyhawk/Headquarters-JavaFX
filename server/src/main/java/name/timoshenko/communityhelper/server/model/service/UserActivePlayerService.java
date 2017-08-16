package name.timoshenko.communityhelper.server.model.service;

import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.UserActivePlayer;

/**
 *
 */
public interface UserActivePlayerService {
    UserActivePlayer getUserActivePlayer(Long userId);
    /**
     * Возвращает активного игрока пользователя с переданным id или new Player(id:0L, userId:0L, nick:"nullPlayer")
     */
    Player getActivePlayer(Long userId);
    UserActivePlayer setActivePlayer(UserActivePlayer userActivePlayer);
}
