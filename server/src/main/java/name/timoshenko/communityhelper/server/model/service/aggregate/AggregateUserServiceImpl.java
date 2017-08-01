package name.timoshenko.communityhelper.server.model.service.aggregate;

import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.domain.UserActivePlayer;
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
import java.util.Optional;

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
        return playerService.findPlayer(userActivePlayerStateService.getActivePlayer(user.getId()).getActivePlayerId())
                .orElseThrow(() -> new NotFoundException("Player not found ny user."));
    }

    @Override
    public List<Player> getPlayersByUserLogin(String userLogin) {
        User user = userService.findUserByLogin(userLogin).orElseThrow(() -> new NotFoundException("User not found"));
        //List<Player> players = playerService.getPlayers(userPlayerService.findIdPlayersByUserId(user.getId()));
        return playerService.getPlayers(userPlayerService.findIdsByUserId(user.getId()));
    }

    @Override
    public Optional<User> getUserByPlayerId(Long playerId) {
        return userService.findUserById(userPlayerService.findByPlayerId(playerId).orElseThrow(() -> new NotFoundException("")).getUserId());
    }
}
