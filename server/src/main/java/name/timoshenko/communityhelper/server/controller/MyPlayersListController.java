package name.timoshenko.communityhelper.server.controller;

import com.canoo.dolphin.BeanManager;
import com.canoo.platform.server.DolphinAction;
import com.canoo.platform.server.DolphinController;
import com.canoo.platform.server.DolphinModel;
import com.canoo.platform.server.binding.PropertyBinder;
import com.canoo.platform.server.event.DolphinEventBus;
import name.timoshenko.communityhelper.common.Constants;
import name.timoshenko.communityhelper.common.model.MyPlayersListWindowModel;
import name.timoshenko.communityhelper.common.model.PlayerModel;
import name.timoshenko.communityhelper.common.model.WorldAdminModel;
import name.timoshenko.communityhelper.server.controller.Security.SecurityContextHolderService;
import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.UserActivePlayer;
import name.timoshenko.communityhelper.server.model.domain.WorldAdminAccess;
import name.timoshenko.communityhelper.server.model.service.PlayerService;
import name.timoshenko.communityhelper.server.model.service.UserActivePlayerService;
import name.timoshenko.communityhelper.server.model.service.WorldAdminAccessService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@DolphinController(Constants.MY_PLAYERS_LIST_CONTROLLER_NAME)
public class MyPlayersListController {

    private final BeanManager beanManager;
    private final PropertyBinder propertyBinder;
    private final DolphinEventBus eventBus;

    private final PlayerService playerService;
    private final SecurityContextHolderService contextHolderService;
    private final UserActivePlayerService userActivePlayerService;
    private final WorldAdminAccessService worldAdminAccessService;

    @DolphinModel
    private MyPlayersListWindowModel model;

    @Autowired
    public MyPlayersListController(BeanManager beanManager,
                                   PropertyBinder propertyBinder,
                                   DolphinEventBus eventBus,
                                   PlayerService playerService,
                                   UserActivePlayerService userActivePlayerService,
                                   WorldAdminAccessService worldAdminAccessService,
                                   SecurityContextHolderService securityContextHolderService) {
        this.beanManager = beanManager;
        this.propertyBinder = propertyBinder;
        this.eventBus = eventBus;
        this.playerService = playerService;
        this.userActivePlayerService = userActivePlayerService;
        this.worldAdminAccessService = worldAdminAccessService;
        this.contextHolderService = securityContextHolderService;
    }

    private List<PlayerModel> getMyPlayers(){
        final List<Player> players = playerService.findPlayersByUserId(contextHolderService.getCurrentUser().getId());
        if (players.isEmpty()) return null;
        return players.stream()
                .map(player -> {
                    return convert(player);
                }).collect(Collectors.toList());
    }

    @DolphinAction(Constants.SHOW_EVENT)
    public void onShow() {
        model.myPlayersProperty().clear();
        model.selectedPlayerProperty().set(null);
        List<PlayerModel> myPlayers = getMyPlayers();
        if (myPlayers != null)
            model.myPlayersProperty().addAll(getMyPlayers());
        Player activePlayer = userActivePlayerService.getActivePlayer(contextHolderService.getCurrentUser().getId());
        if (activePlayer != null && activePlayer.getId() != -1)
            model.selectedPlayerProperty().set(convert(activePlayer));
            //model.myActivePlayerNicknameProperty().set(convert(activePlayer).getNickName());
        model.newPlayerProperty().set(beanManager.create(PlayerModel.class));
    }

    @DolphinAction(Constants.MYPLAYERS_LIST_WINDOW_EVENT_SET_ACTIVE_PLAYER)
    public void onSetActivePlayer() {
        UserActivePlayer oldActivePlayer = userActivePlayerService.getUserActivePlayer(contextHolderService.getCurrentUser().getId());
        Long id = oldActivePlayer.getId();
        UserActivePlayer newActivePlayer = new UserActivePlayer(id,
                contextHolderService.getCurrentUser().getId(),
                model.selectedPlayerProperty().get().getId());
        userActivePlayerService.setActivePlayer(newActivePlayer);
    }


    @DolphinAction(Constants.MYPLAYERS_LIST_WINDOW_EVENT_ADD_NEW_PLAYER)
    public void onClickAddNewPlayerButton() {
        Player savedPlayer = playerService.addPlayer(convert(model.newPlayerProperty().get()));
        if (savedPlayer != null) model.myPlayersProperty().add(convert(savedPlayer));
    }

    @PostConstruct
    public void init() {
        propertyBinder.bind(model.windowVisibleProperty(), Qualifiers.MY_PLAYERS_LIST_WINDOW_VISIBLE_QUALIFIER);

        model.worldsProperty().addAll(worldAdminAccessService.getAll().stream().map(world -> {return convert(world);}).collect(Collectors.toList()));
    }

    private PlayerModel convert(Player player){
        final PlayerModel playerModel = beanManager.create(PlayerModel.class);
        playerModel.idProperty().set(player.getId());
        playerModel.nicknameProperty().set(player.getNick());
        playerModel.worldProperty().set(convert(worldAdminAccessService.getWorld(player.getWorldId())));
        return playerModel;
    }

    private Player convert(PlayerModel playerModel) {
        return new Player(-1L,
                contextHolderService.getCurrentUser().getId(),
                playerModel.getWorld().getId(),
                playerModel.getNickName());

    }

    private WorldAdminModel convert(WorldAdminAccess world) {
        final WorldAdminModel worldModel = beanManager.create(WorldAdminModel.class);
        worldModel.idProperty().set(world.getId());
        worldModel.nameProperty().set(world.getName());
        return worldModel;
    }


}
