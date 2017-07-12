package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.common.model.CurrentUserModel;
import name.timoshenko.communityhelper.server.model.repositories.PlayerRepository;
import name.timoshenko.communityhelper.server.model.service.PlayerService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service("basicPlayerService")
//@Secured(value = {"ROLE_ADMIN"})
public class BasicPlayerService implements PlayerService {

    private final PlayerRepository playerRepository;

    public BasicPlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    //@PreAuthorize("#user")
    //@PreAuthorize("hasRole('ROLE_USER')")
    //@PostFilter("hasRole('ROLE_USER') or filterObject.assignee == authentication.name")
    //@PreFilter("hasRole('ROLE_USER')")
    //@PostFilter("hasPermission(filterObject, 'read') or hasPermission(filterObject, 'admin')")
    //public Optional<Player> findPlayer(long id, @P("user") String user) {
    public Optional<Player> findPlayer(long id) {
        //if (user == "") return null;
        //if (user == "user1") return Optional.ofNullable(playerRepository.findOne(id));
        //if (user != "user1") return Optional.ofNullable(playerRepository.findOne(1L));
        return Optional.ofNullable(playerRepository.findOne(id));
    }

    //@Secured(value = {"ROLE_ADMIN"})

    @Override
    //@PostFilter("hasRole('ROLE_ADMIN')")
    //@Secured({"ROLE_USER", "ACL_REPORT_ACCEPT"})
    @Secured({"ACL_REPORT_ACCEPT"})
    public List<Player> getPlayers(List<Long> id) {
        return playerRepository.findAll(id);
    }


}
