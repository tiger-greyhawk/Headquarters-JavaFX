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

    @Override
    public Player getActivePlayer(Long userId) {
        if (userId == 0L) return new Player(0L, 0L, "nullPlayer");
        return playerService.findPlayer(userActivePlayerRepository.findByUserId(userId)
                .orElseThrow(()->new NotFoundException("не найден активный игрок данного пользователя"))
                .getActivePlayerId())
                .orElseThrow(()->new NotFoundException("не найден активный игрок данного пользователя"));
    }

    @Override
    public UserActivePlayer setActivePlayer(UserActivePlayer userActivePlayer) {
        return userActivePlayerRepository.save(userActivePlayer);
    }
}
