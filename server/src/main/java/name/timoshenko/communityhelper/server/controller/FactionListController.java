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
import name.timoshenko.communityhelper.common.model.WorldAdminModel;
import name.timoshenko.communityhelper.server.controller.Security.SecurityContextHolderService;
import name.timoshenko.communityhelper.server.controller.facade.FactionListFacade;
import name.timoshenko.communityhelper.server.model.domain.*;
import name.timoshenko.communityhelper.server.model.service.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;

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

    private org.slf4j.Logger LOG = LoggerFactory.getLogger(FactionListController.class);

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
    private final FactionAllyService factionAllyService;
    @Autowired
    private final WorldAdminAccessService worldsService;
    @Autowired
    private final FactionListFacade factionListFacade;

    @DolphinModel
    private FactionListWindowModel model;

    /**
     * Тут всё ижектится с помощью spring-а.
     */
    @Autowired
    public FactionListController(@Qualifier("cachedFactionService") FactionService factionService,
                                 //@Qualifier("basicFactionService") FactionService factionService,
                                 FactionPlayerService factionPlayerService,
                                 PlayerService playerService,
                                 UserDetailsService userService,
                                 BeanManager beanManager,
                                 PropertyBinder propertyBinder,
                                 SecurityContextHolderService securityContextHolderService,
                                 UserActivePlayerService userActivePlayerService,
                                 FactionAllyService factionAllyService,
                                 WorldAdminAccessService worldAdminAccessService,
                                 FactionListFacade factionListFacade) {
        this.factionService = factionService;
        this.factionPlayerService = factionPlayerService;
        this.playerService = playerService;
        this.userService = userService;
        this.beanManager = beanManager;
        this.propertyBinder = propertyBinder;
        this.contextHolderService = securityContextHolderService;
        this.userActivePlayerService = userActivePlayerService;
        this.factionAllyService = factionAllyService;
        this.worldsService = worldAdminAccessService;
        this.factionListFacade = factionListFacade;
    }


    private Collection<FactionModel> getFactionList(final String filter) {
        final List<Faction> factions = factionListFacade.getFactions(filter);
        return factions.stream()
                .map(faction -> {
                    return convert(faction);
                }).collect(Collectors.toList());
    }

    private Collection<WorldAdminModel> getWorlds(){
        final List<WorldAdminAccess> worlds = worldsService.getAll();
        return worlds.stream()
                .map(world -> {
                    final WorldAdminModel worldAdminModel = beanManager.create(WorldAdminModel.class);
                    worldAdminModel.idProperty().set(world.getId());
                    worldAdminModel.nameProperty().set(world.getName());
                    return worldAdminModel;
                }).collect(Collectors.toList());
    }


    private Collection<PlayerModel> getPlayers(final Long factionId) {
        final List<Long> factionPlayersIds = factionPlayerService.findPlayerIdsByFactionId(factionId).stream().collect(Collectors.toList());

        List<Player> players = new ArrayList<Player>();
        players = playerService.getPlayers(factionPlayersIds);
        return players.stream()
                .map(player -> {
                    return convert(player);
                }).collect(Collectors.toList());
    }

    @DolphinAction(Constants.DELETE_FACTION_EVENT)
    private void deleteFaction(){
        factionService.deleteFaction(model.selectedFactionProperty().get().getId());
        factionPlayerService.deletePlayersFromFaction(model.selectedFactionProperty().get().getId());
        model.factionsProperty().remove(model.selectedFactionProperty().get());
    }

    @DolphinAction(Constants.CREATE_FACTION_EVENT)
    private void createFaction(@Param("factionName") String factionName, @Param("factionSlogan") String factionSlogan, @Param("worldId") String worldId){
        Faction myFaction = factionListFacade.getMyCurrentFaction().orElse(new Faction(-1L, "", "", -1L, -1L));
        if (myFaction.getId() != -1L) return;
        model.factionsProperty().add(convert(factionListFacade.createFaction(factionName, factionSlogan, Long.parseLong(worldId))));
        model.selectedPlayerProperty().get().canNotPlayerCreateFactionProperty().set(true);
    }

    @DolphinAction(Constants.SET_ALLY_FACTION_EVENT)
    private void createAlliesFaction(@Param("allyNote") String note, @Param("allyType") String typeAlly){
        Faction myFaction = factionListFacade.getMyCurrentFaction().orElse(new Faction(-1L, "", "", -1L, -1L));
        FactionModel factionModelToAlly = model.selectedFactionProperty().get();
        if (factionModelToAlly.equals(myFaction) || myFaction.getId() == -1L) return;

        FactionAlly toAlly = new FactionAlly(0L, myFaction.getId(), factionModelToAlly.getId(), note, typeAlly, new Date());
        FactionAlly alliedFaction = factionAllyService.save(toAlly);
        factionModelToAlly.typeAllyProperty().set(alliedFaction.getAllyType());
        model.factionsProperty().set(model.factionsProperty().indexOf(factionModelToAlly), factionModelToAlly);
        model.selectedFactionProperty().set(factionModelToAlly);
    }

    @DolphinAction(Constants.JOIN_FACTION_EVENT)
    private void joinToFaction(){
        Faction myFaction = factionListFacade.getMyCurrentFaction().orElse(new Faction(-1L, "", "", -1L, -1L));
        if (myFaction.getId() != -1L) return;
        factionPlayerService.addPlayerToFaction(userActivePlayerService.getActivePlayer(contextHolderService.getCurrentUser().getId()),
                factionService.getFactionById(model.selectedFactionProperty().get().getId()).orElse(new Faction()),
                false,
                false);
        model.playersProperty().clear();
        model.playersProperty().addAll(getPlayers(model.selectedFactionProperty().get().getId()));
        model.selectedPlayerProperty().get().canNotPlayerCreateFactionProperty().set(true);
    }

    @DolphinAction(Constants.LEAVE_FACTION_EVENT)
    private void leaveFaction(){
        Faction myFaction = factionListFacade.getMyCurrentFaction().orElse(new Faction(-1L, "", "", -1L, -1L));
        if (myFaction.getId() == -1L) return;
        factionPlayerService.deletePlayerFromFaction(myFaction.getId(), userActivePlayerService.getActivePlayer(contextHolderService.getCurrentUser().getId()).getId());

        model.playersProperty().clear();
        model.playersProperty().addAll(getPlayers(model.selectedFactionProperty().get().getId()));
        model.selectedPlayerProperty().get().canNotPlayerCreateFactionProperty().set(false);
    }

    @DolphinAction(Constants.SHOW_EVENT)
    public void onShow(){
        model.factionsProperty().clear();
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
                model.cannotDeleteCurrentFactionProperty()
                        .set(!factionListFacade.isCurrentUserOwnsCurrentFaction(model.selectedFactionProperty()
                                .get().getOwnerId()));
            }
        });
        model.worldsProperty().clear();
        model.worldsProperty().addAll(getWorlds());

        if (!factionListFacade.getMyCurrentFaction().isPresent())
            model.selectedPlayerProperty().get().canNotPlayerCreateFactionProperty().set(false);
        else model.selectedPlayerProperty().get().canNotPlayerCreateFactionProperty().set(true);
    }

    /**
     * Метод, выполняющийся при подключении клиента и выполняющий основную приязку к событиям.
     */
    @PostConstruct
    public void init() {
        propertyBinder.bind(model.windowVisibleProperty(), Qualifiers.FACTION_WINDOW_VISIBLE_QUALIFIER);
        propertyBinder.bind(model.currentUserModelProperty(), Qualifiers.CURRENT_USER_MODEL_QUALIFIER);
        model.selectedPlayerProperty().set(convert(userActivePlayerService.getActivePlayer(contextHolderService.getCurrentUser().getId())));

    }

    private FactionModel convert(Faction faction){
        final FactionModel factionModel = beanManager.create(FactionModel.class);
        factionModel.idProperty().set(faction.getId());
        factionModel.nameProperty().set(faction.getName());
        factionModel.worldProperty().set(worldsService.getWorld(faction.getWorldId()).getName());
        factionModel.sloganProperty().set(faction.getSlogan());
        factionModel.ownerIdProperty().set(faction.getOwnerId());
        factionModel.ownerNameProperty().set(
                playerService.findPlayer(faction.getOwnerId()).map(Player::getNick).orElse("")
        );
        factionModel.typeAllyProperty().set(factionListFacade.getAlliedStatus(faction).orElse(""));
        return factionModel;
    }

    private PlayerModel convert(Player player){
        final PlayerModel playerModel = beanManager.create(PlayerModel.class);
        playerModel.idProperty().set(player.getId());
        playerModel.nicknameProperty().set(player.getNick());
        playerModel.userIdProperty().set(player.getUserId());
        return playerModel;
    }
}
