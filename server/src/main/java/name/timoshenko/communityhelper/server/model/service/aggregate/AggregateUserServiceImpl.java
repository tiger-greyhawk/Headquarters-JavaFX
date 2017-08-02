package name.timoshenko.communityhelper.server.model.service.aggregate;

//import javassist.NotFoundException;
import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.service.PlayerService;
import name.timoshenko.communityhelper.server.model.service.UserActivePlayerService;
import name.timoshenko.communityhelper.server.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 *
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class AggregateUserServiceImpl implements AggregateUserService {

    @Autowired
    private UserActivePlayerService userActivePlayerService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private UserService userService;

    @Override
    public Optional<Player> getActivePlayerByUserId(Long userId) {
        return playerService.findPlayer(userActivePlayerService.getActivePlayer(userId).getActivePlayerId());
    }

    @Override
    public List<Player> getPlayersByUserId(Long userId) {
        return playerService.findPlayersByUserId(userId);
    }


    @Override
    public User getUserByPlayerId(Long playerId) {
        return userService.findUserById(playerService.findPlayer(playerId)
                .orElseThrow(()->new NotFoundException("игрок с данным id не найден"))
                .getUserId()).orElseThrow(()->new NotFoundException("пользователь данного игрока не найден"));

    }
}
