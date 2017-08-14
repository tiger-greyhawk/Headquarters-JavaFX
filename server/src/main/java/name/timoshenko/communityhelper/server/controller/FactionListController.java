package name.timoshenko.communityhelper.server.controller;

import com.canoo.dolphin.BeanManager;
import com.canoo.platform.server.DolphinAction;
import com.canoo.platform.server.DolphinController;
import com.canoo.platform.server.DolphinModel;
import com.canoo.platform.server.Param;
import com.canoo.platform.server.binding.PropertyBinder;
import name.timoshenko.communityhelper.common.Constants;
import name.timoshenko.communityhelper.common.model.FactionListWindowModel;
import name.timoshenko.communityhelper.common.model.FactionModel;
import name.timoshenko.communityhelper.common.model.PlayerModel;
import name.timoshenko.communityhelper.server.controller.Security.SecurityContextHolderService;
import name.timoshenko.communityhelper.server.model.domain.AllyOfFaction;
import name.timoshenko.communityhelper.server.model.domain.Faction;
import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetailsService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
    private final UserDetailsService userService;
    private final BeanManager beanManager;
    private final PropertyBinder propertyBinder;


    @Autowired
    private final SecurityContextHolderService contextHolderService;
    @Autowired
    private final UserActivePlayerService userActivePlayerService;
    @Autowired
    private final AllyOfFactionService allyOfFactionService;

    @DolphinModel
    private FactionListWindowModel model;

    /**
     * Тут всё ижектится с помощью spring-а.
     */
    @Autowired
    public FactionListController(@Qualifier("cachedFactionService") FactionService factionService,
                                 FactionPlayerService factionPlayerService,
                                 PlayerService playerService,
                                 UserDetailsService userService,
                                 BeanManager beanManager,
                                 PropertyBinder propertyBinder,
                                 SecurityContextHolderService securityContextHolderService,
                                 UserActivePlayerService userActivePlayerService,
                                 AllyOfFactionService allyOfFactionService) {
        this.factionService = factionService;
        this.factionPlayerService = factionPlayerService;
        this.playerService = playerService;
        this.userService = userService;
        this.beanManager = beanManager;
        this.propertyBinder = propertyBinder;
        this.contextHolderService = securityContextHolderService;
        this.userActivePlayerService = userActivePlayerService;
        this.allyOfFactionService = allyOfFactionService;
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
                    factionModel.typeAllyProperty().set(getAlliedStatus(faction));
                    return factionModel;
                }).collect(Collectors.toList());
    }

    private String getAlliedStatus(Faction factionToCheck){
        User currentUser = contextHolderService.getCurrentUser();
        if ((currentUser == null) || (currentUser.getId().equals(0L)))return null;
        Faction myFaction = getMyCurrentFaction();
        String result = "";
        /*TODO переделать List<AllyOfFaction>
        Здесь слишком дорого объявлять этот массив.
         */
        final List<AllyOfFaction> allyFactions = allyOfFactionService.findAllyFactions(myFaction.getId());
        for (AllyOfFaction ally:allyFactions){
            if ((ally.getFirstFactionId().equals(myFaction.getId()) &&  ally.getSecondFactionId().equals(factionToCheck.getId()))
                || (ally.getFirstFactionId().equals(factionToCheck.getId()) && ally.getSecondFactionId().equals(myFaction.getId())))
                //result = ally.getAllyType().name();
                result = ally.getNote();
        }
        return result;

    }

    private Faction getMyCurrentFaction(){
        return factionService.findFactionByOwnerId(
                userActivePlayerService.getActivePlayer(
                        contextHolderService.getCurrentUser().getId()
                ).getId()
        );
    }

    private Collection<PlayerModel> getPlayers(final Long factionId) {
        final List<Long> factionPlayersIds = factionPlayerService.findPlayersByFactionId(factionId).stream().collect(Collectors.toList());

        List<Player> factionPlayers = new ArrayList<Player>();
        try {
            factionPlayers = playerService.getPlayers(factionPlayersIds);
        }
        /*TODO AccessDeniedExcecption
        Разобраться или удалить
         */
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
        final Long factionOwnerId = model.selectedFactionProperty().get().getOwnerId();
        Player activePlayer = userActivePlayerService.getActivePlayer(contextHolderService.getCurrentUser().getId());
        return factionOwnerId.equals(activePlayer.getId());
    }

    @DolphinAction(Constants.DELETE_FACTION_EVENT)
    private void deleteFaction(){
        try {
            factionService.deleteFaction(model.selectedFactionProperty().get().getId());
            model.factionsProperty().remove(model.selectedFactionProperty().get());
        }
        catch (AccessDeniedException e){
        }
    }

    @DolphinAction(Constants.CREATE_FACTION_EVENT)
    private void createFaction(@Param Faction factionToCreate){
        try {
            Faction faction = factionService.createFaction(factionToCreate);
            final FactionModel factionModel = beanManager.create(FactionModel.class);
            factionModel.idProperty().set(faction.getId());
            factionModel.nameProperty().set(faction.getName());
            factionModel.ownerIdProperty().set(userActivePlayerService.getActivePlayer(contextHolderService.getCurrentUser().getId())
                    .getId());
            factionModel.ownerNameProperty().set(userActivePlayerService.getActivePlayer(contextHolderService.getCurrentUser().getId())
                    .getNick());
            model.factionsProperty().add(factionModel);
        }
        catch (AccessDeniedException e){
            throw new NotImplementedException();
        }
    }

    @DolphinAction(Constants.CREATE_ALLIES_FACTION_EVENT)
    private void createAlliesFaction(@Param("notation") String notation, @Param("faction") Long factionModel){
        Faction myFaction = getMyCurrentFaction();
        FactionModel factionModelToAlly = model.selectedFactionProperty().get();
        if (factionModelToAlly.equals(myFaction)) return;
        AllyOfFaction toAlly = new AllyOfFaction(0L, myFaction.getId(), factionModelToAlly.getId(), notation, AllyOfFaction.allyTypeEnum.NULLY, new Date());


        try {
            AllyOfFaction alliedFaction = allyOfFactionService.save(toAlly);
            factionModelToAlly.typeAllyProperty().set(alliedFaction.getAllyType().name());
            model.factionsProperty().set(model.factionsProperty().indexOf(factionModelToAlly), factionModelToAlly);
            model.selectedFactionProperty().set(factionModelToAlly);
        }
        catch (AccessDeniedException e){
            throw new NotImplementedException();
        }
    }

    /**
     * Метод, выполняющийся при подключении клиента и выполняющий основную приязку к событиям.
     */
    @PostConstruct
    @DolphinAction(Constants.LOGIN_EVENT)
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
