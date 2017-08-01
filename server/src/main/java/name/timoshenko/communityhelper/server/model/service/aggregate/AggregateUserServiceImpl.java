package name.timoshenko.communityhelper.server.model.service.aggregate;

//import javassist.NotFoundException;
import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.service.PlayerService;
import name.timoshenko.communityhelper.server.model.service.UserActivePlayerService;
import name.timoshenko.communityhelper.server.model.service.UserPlayerService;
import name.timoshenko.communityhelper.server.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



/**
 *
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class AggregateUserServiceImpl implements AggregateUserService {

    @Autowired
    private UserActivePlayerService userActivePlayerService;
    @Autowired
    private UserPlayerService userPlayerService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private UserService userService;

    @Override
    public Player getActivePlayerByUserId(Long userId) {
        Player activePlayer = playerService.findPlayer(userActivePlayerService.getActivePlayer(userId).getActivePlayerId())
                .orElseThrow(() -> new NotFoundException("Player not found ny user."));
        if (!userPlayerService.findByPlayerId(activePlayer.getId()).orElseThrow(() -> new NotFoundException("в user-player нет игрока с таким id"))
                .getUserId().equals(userId)) throw new NotFoundException("Игрок не принадлежит текущему пользователю!!!");
        return activePlayer;
    }

    @Override
    public List<Player> getPlayersByUserId(Long userId) {
        return playerService.getPlayers(userPlayerService.findIdsByUserId(userId));
    }


    @Override
    public User getUserByPlayerId(Long playerId) {
        return userService.findUserById(userPlayerService.findByPlayerId(playerId).orElseThrow(() -> new NotFoundException("сопоставление user-player не найдено")).getUserId())
                .orElseThrow(() -> new NotFoundException("пользователь этого игрока не найден"));
    }
}
