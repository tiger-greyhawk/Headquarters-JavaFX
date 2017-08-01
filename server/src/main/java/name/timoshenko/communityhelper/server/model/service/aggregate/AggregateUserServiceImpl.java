package name.timoshenko.communityhelper.server.model.service.aggregate;

//import javassist.NotFoundException;
import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.service.PlayerService;
import name.timoshenko.communityhelper.server.model.service.UserActivePlayerStateService;
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
    private UserActivePlayerStateService userActivePlayerStateService;
    @Autowired
    private UserPlayerService userPlayerService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private UserService userService;

    @Override
    public Player getActivePlayerByUserLogin(String userLogin) {
        User user = userService.findUserByLogin(userLogin).orElseThrow(() -> new NotFoundException("User not found"));
        Player activePlayer = playerService.findPlayer(userActivePlayerStateService.getActivePlayer(user.getId()).getActivePlayerId())
                .orElseThrow(() -> new NotFoundException("Player not found ny user."));
        if (!userPlayerService.findByPlayerId(activePlayer.getId()).orElseThrow(() -> new NotFoundException("в user-player нет игрока с таким id"))
                .getUserId().equals(user.getId())) throw new NotFoundException("Игрок не принадлежит текущему пользователю!!!");
        return activePlayer;
    }

    @Override
    public List<Player> getPlayersByUserLogin(String userLogin) {
        User user = userService.findUserByLogin(userLogin).orElseThrow(() -> new NotFoundException("User not found"));
        return playerService.getPlayers(userPlayerService.findIdsByUserId(user.getId()));
    }

    @Override
    public User getUserByPlayerId(Long playerId) {
        return userService.findUserById(userPlayerService.findByPlayerId(playerId).orElseThrow(() -> new NotFoundException("сопоставление user-player не найдено")).getUserId())
                .orElseThrow(() -> new NotFoundException("пользователь этого игрока не найден"));
    }
}
