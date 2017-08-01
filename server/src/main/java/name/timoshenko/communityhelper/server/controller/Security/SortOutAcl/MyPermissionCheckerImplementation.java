package name.timoshenko.communityhelper.server.controller.Security.SortOutAcl;

import name.timoshenko.communityhelper.server.model.domain.Faction;
import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.service.FactionService;
import name.timoshenko.communityhelper.server.model.service.basic.acl.AclClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 *
 */
public class MyPermissionCheckerImplementation implements MyPermissionChecker {

    @Autowired
    FactionService factionService;

    @Autowired
    AclClassService aclClassService;

    public boolean hasPermission(Authentication authentication, Object target, Object permission) {
        target.getClass().getName();

        aclClassService.findByobjectIdentityClass(target.getClass().getName());
        //if (target instanceof)
        return false;
    }

    @Override
    public boolean checkPermission(Long factionId) {
        List<Player> playersOfUser = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPlayers();
        Faction faction = factionService.getFactionById(factionId).orElse(null);
        if (faction != null)
        for (Player player : playersOfUser) {
            if (player.getId().equals(faction.getOwnerId()))
                return true;
        }
        return false;
    }

    @Override
    public void setPermission() {

    }
}
