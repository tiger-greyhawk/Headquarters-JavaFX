package name.timoshenko.communityhelper.server.controller;

import com.canoo.dolphin.BeanManager;
import com.canoo.platform.server.DolphinController;
import com.canoo.platform.server.DolphinModel;
import com.canoo.platform.server.binding.PropertyBinder;
import com.canoo.platform.server.event.DolphinEventBus;
import name.timoshenko.communityhelper.common.Constants;
import name.timoshenko.communityhelper.common.model.CurrentUserModel;
import name.timoshenko.communityhelper.common.model.FactionListWindowModel;
import name.timoshenko.communityhelper.common.model.FactionModel;
import name.timoshenko.communityhelper.common.model.PlayerModel;
import name.timoshenko.communityhelper.server.controller.Security.UserDetailsServiceDolphin;
import name.timoshenko.communityhelper.server.model.domain.Faction;
import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.service.FactionPlayerService;
import name.timoshenko.communityhelper.server.model.service.FactionService;
import name.timoshenko.communityhelper.server.model.service.PlayerService;
import name.timoshenko.communityhelper.server.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.model.AclService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Это контроллер, который связывает модель и код, откликаясь на события.
 * Такой контроллер создается при каждом подключеии клиента.
 */
@DolphinController(Constants.FACTION_LIST_CONTROLLER_NAME)
public class FactionListController {

    private final FactionService factionService;
    private final FactionPlayerService factionPlayerService;
    private final PlayerService playerService;
    //private final UserDetailsService userService;
    private final BeanManager beanManager;
    private final PropertyBinder propertyBinder;



    @DolphinModel
    private FactionListWindowModel model;

    /**
     * Тут всё ижектится с помощью spring-а.
     */
    @Autowired
    public FactionListController(@Qualifier("cachedFactionService") FactionService factionService,
                                 FactionPlayerService factionPlayerService,
                                 PlayerService playerService,
                                 //UserDetailsService userService,
                                 BeanManager beanManager,
                                 PropertyBinder propertyBinder) {
        this.factionService = factionService;
        this.factionPlayerService = factionPlayerService;
        this.playerService = playerService;
        //this.userService = userService;
        this.beanManager = beanManager;
        this.propertyBinder = propertyBinder;
    }

    private Collection<FactionModel> getFactionList(final String filter) {
        final List<Faction> factions = factionService.getFactions(filter);
        return factions.stream()
                .map(faction -> {
                    final FactionModel factionModel = beanManager.create(FactionModel.class);
                    factionModel.idProperty().set(faction.getId());
                    factionModel.nameProperty().set(faction.getName());
                    factionModel.ownerIdProperty().set(faction.getOwnerId());
                    factionModel.ownerNameProperty().set(
                            playerService.findPlayer(faction.getOwnerId()).map(Player::getNick).orElse("")
                    );
                    return factionModel;
                }).collect(Collectors.toList());
    }

    private Collection<PlayerModel> getPlayers(final Long factionId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final List<Long> factionPlayersIds = factionPlayerService.findPlayersByFactionId(factionId).stream().collect(Collectors.toList());

        List<Player> factionPlayers = new ArrayList<Player>();
        try {
            factionPlayers = playerService.getPlayers(factionPlayersIds);
        }
        catch (AccessDeniedException accessException) {
            throw new AccessDeniedException("access denied");
        }
        return factionPlayers.stream()
                .map(player -> {
                    final PlayerModel playerModel = beanManager.create(PlayerModel.class);
                    playerModel.idProperty().set(player.getId());
                    playerModel.nicknameProperty().set(player.getNick());
                    playerModel.userIdProperty().set(player.getUserId());
                    return playerModel;
                }).collect(Collectors.toList());
    }

    private boolean isCurrentUserOwnsCurrentFaction() {
        final Long userId = model.currentUserModelProperty().get().userIdProperty().get();
        final Long factionOwnerId = model.selectedFactionProperty().get().getOwnerId();
        UserDetailsServiceDolphin userDetails = (UserDetailsServiceDolphin) SecurityContextHolder.getContext().getAuthentication().getDetails();
        CurrentUserModel user = model.currentUserModelProperty().get();

        return playerService.findPlayer(factionOwnerId)
                .map(Player::getUserId)
                .map(id -> id.equals(userId)).orElse(false);
    }

    /*private CurrentUserModel setUserFromContext(CurrentUserModel springSecurityUserModel)
    {
        if (springSecurityUserModel == null) return null;
        UserDetails user = userDetailsService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
                //.orElseThrow(() -> new UsernameNotFoundException("No User Was Found From Context. What Dumn"));
        //springSecurityUserModel.loginProperty().set(user.getLogin());
        //springSecurityUserModel.userIdProperty().set(user.getId());
        springSecurityUserModel.loggedInProperty().set(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        return springSecurityUserModel;
    }*/

    /**
     * Метод, выполняющийся при подключении клиента и выполняющий основную приязку к событиям.
     */
    @PostConstruct
    public void init() {
        propertyBinder.bind(model.windowVisibleProperty(), Qualifiers.FACTION_WINDOW_VISIBLE_QUALIFIER);
        propertyBinder.bind(model.currentUserModelProperty(), Qualifiers.CURRENT_USER_MODEL_QUALIFIER);
        model.factionsProperty().addAll(getFactionList(""));
        model.filterProperty().onChanged(v -> {
                    model.factionsProperty().clear();
                    model.factionsProperty().addAll(getFactionList(v.getNewValue()));
                }
        );
        model.cannotDeleteCurrentFactionProperty().set(true);
        model.selectedFactionProperty().onChanged(v -> {
            model.playersProperty().clear();
            model.cannotDeleteCurrentFactionProperty().set(true);
            if (v != null && v.getNewValue() != null) {
                model.playersProperty().addAll(getPlayers(v.getNewValue().getId()));
                model.cannotDeleteCurrentFactionProperty().set(!isCurrentUserOwnsCurrentFaction());
            }
        });
    }

}
