package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.UserActivePlayer;
import name.timoshenko.communityhelper.server.model.repositories.UserActivePlayerRepository;
import name.timoshenko.communityhelper.server.model.service.PlayerService;
import name.timoshenko.communityhelper.server.model.service.UserActivePlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class BasicUserActivePlayerService implements UserActivePlayerService {

    @Autowired
    UserActivePlayerRepository userActivePlayerRepository;
    @Autowired
    PlayerService playerService;

    @Override
    public UserActivePlayer getUserActivePlayer(Long userId) {
        return userActivePlayerRepository.findByUserId(userId).orElseThrow(()-> new NotFoundException("Cant found active player by this user"));
    }

    /**
     * Возвращает активного игрока пользователя с переданным id или new Player(id:-1L, userId:-1L, nick:"nullPlayer")
     */
    @Override
    public Player getActivePlayer(Long userId) {
        if (userId == -1L) return new Player(-1L, -1L, -1L, "nullPlayer");
        return playerService.findPlayer(userActivePlayerRepository.findByUserId(userId)
                .orElse(new UserActivePlayer(-1L, -1L, -1L))  // находим связь User - ActivePlayer
                .getActivePlayerId())
                .orElse(new Player(-1L, -1L, -1L, "nullPlayer")); // находим и возвращаем игрока с id найденном в "связи"
    }

    @Override
    public UserActivePlayer setActivePlayer(UserActivePlayer userActivePlayer) {
        return userActivePlayerRepository.save(userActivePlayer);
    }
}
