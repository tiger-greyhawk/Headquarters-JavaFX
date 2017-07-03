package name.timoshenko.communityhelper.server.controller;

import com.canoo.dolphin.BeanManager;
import com.canoo.platform.server.DolphinController;
import com.canoo.platform.server.DolphinModel;
import com.canoo.platform.server.event.DolphinEventBus;
import name.timoshenko.communityhelper.common.Constants;
import name.timoshenko.communityhelper.common.model.CurrentUserModel;
import name.timoshenko.communityhelper.common.model.FactionListModel;
import name.timoshenko.communityhelper.common.model.FactionModel;
import name.timoshenko.communityhelper.common.model.PlayerModel;
import name.timoshenko.communityhelper.server.model.domain.Faction;
import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.service.FactionPlayerService;
import name.timoshenko.communityhelper.server.model.service.FactionService;
import name.timoshenko.communityhelper.server.model.service.PlayerService;
import name.timoshenko.communityhelper.server.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
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
    private final UserService userService;
    private final BeanManager beanManager;
    private final DolphinEventBus eventBus;

    @DolphinModel
    private FactionListModel model;

    /**
     * Тут всё ижектится с помощью spring-а.
     */
    @Autowired
    public FactionListController(@Qualifier("cachedFactionService") FactionService factionService,
                                 FactionPlayerService factionPlayerService,
                                 PlayerService playerService,
                                 UserService userService,
                                 BeanManager beanManager,
                                 DolphinEventBus eventBus) {
        this.factionService = factionService;
        this.factionPlayerService = factionPlayerService;
        this.playerService = playerService;
        this.userService = userService;
        this.beanManager = beanManager;
        this.eventBus = eventBus;
    }

    private Collection<FactionModel> getFactionList(final String filter) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) return Collections.EMPTY_LIST;
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
        final List<Long> factionPlayersIds = factionPlayerService.findPlayersByFactionId(factionId).stream().collect(Collectors.toList());
        final List<Player> factionPlayers = playerService.getPlayers(factionPlayersIds);
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
        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //String principalName = ((UserDetails)principal).getUsername();
        CurrentUserModel currentUser = model.currentUserModelProperty().get();
        final Long userId = model.currentUserModelProperty().get().userIdProperty().get();
        final Long factionOwnerId = model.selectedFactionProperty().get().getOwnerId();
        return playerService.findPlayer(factionOwnerId)
                .map(Player::getUserId)
                .map(id -> id.equals(userId)).orElse(false);
    }

    private boolean haveCurrentUserRightToAction()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = ((UserDetails)principal).getUsername();
        User user = userService.findUserByLogin(login).orElseThrow(() -> new UsernameNotFoundException("No User With Login \"" + login + "\" Was Found"));
        return false;
    }

    /**
     * Метод, выполняющийся при подключении клиента и выполняющий основную приязку к событиям.
     */
    @PostConstruct
    public void init() {

        //TODO здесь мы создаем новую модель Юзера. А надо подсовывать существующую уже авторизованного на данный момент.
        //model.currentUserModelProperty().set(beanManager.create(CurrentUserModel.class));
        //model.currentUserModelProperty().get().loggedInProperty().set(false);
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

        eventBus.subscribe(EventTopics.LOGIN_TOPIC, m -> {
            System.err.println("LOGGED IN: " + m.getData().loggedInProperty().get());
            model.currentUserModelProperty().get().loggedInProperty().set(m.getData().loggedInProperty().get());
            model.currentUserModelProperty().get().userIdProperty().set(m.getData().userIdProperty().get());
            model.currentUserModelProperty().get().loginProperty().set(m.getData().loginProperty().get());
        });
    }

}
