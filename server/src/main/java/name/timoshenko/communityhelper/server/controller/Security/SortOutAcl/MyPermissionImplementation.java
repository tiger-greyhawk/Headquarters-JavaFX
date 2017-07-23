package name.timoshenko.communityhelper.server.controller.Security.SortOutAcl;

import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.service.FactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 *
 */
public class MyPermissionImplementation implements MyPermission {

    @Autowired
    FactionService factionService;

    @Override

    public boolean checkPermission(int factionId) {
        List<Player> playersOfUser = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPlayers();
        for (Player player : playersOfUser) {
            //if (player.getId().equals(factionService.getFactionById(factionId)))
                return true;
        }
        return false;
    }

    @Override
    public void setPermission() {

    }
}
