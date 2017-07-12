package name.timoshenko.communityhelper.server.model.service;

import name.timoshenko.communityhelper.common.model.CurrentUserModel;
import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.User;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface PlayerService {
    //@PreAuthorize("#username == principal.username")
    Optional<Player> findPlayer(long id);

    List<Player> getPlayers(List<Long> id);
}
