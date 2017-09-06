package name.timoshenko.communityhelper.server.controller;

import com.canoo.dolphin.BeanManager;
import com.canoo.platform.server.DolphinAction;
import com.canoo.platform.server.DolphinController;
import com.canoo.platform.server.DolphinModel;
import com.canoo.platform.server.binding.PropertyBinder;
import name.timoshenko.communityhelper.common.Constants;
import name.timoshenko.communityhelper.common.model.PlayerModel;
import name.timoshenko.communityhelper.common.model.RequestResourceListWindowModel;
import name.timoshenko.communityhelper.common.model.RequestResourceModel;
import name.timoshenko.communityhelper.common.model.WorldAdminModel;
import name.timoshenko.communityhelper.server.controller.Security.SecurityContextHolderService;
import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.RequestResource;
import name.timoshenko.communityhelper.server.model.domain.WorldAdminAccess;
import name.timoshenko.communityhelper.server.model.service.PlayerService;
import name.timoshenko.communityhelper.server.model.service.RequestResourceService;
import name.timoshenko.communityhelper.server.model.service.UserActivePlayerService;
import name.timoshenko.communityhelper.server.model.service.WorldAdminAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.acls.model.NotFoundException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

@DolphinController(Constants.REQUEST_RESOURCE_LIST_CONTROLLER_NAME)
//@EnableAutoConfiguration
@EnableScheduling
@EnableAsync
public class RequestResourceListController {

    @Autowired
    private final PropertyBinder propertyBinder;
    @Autowired
    private final BeanManager beanManager;

    @Autowired
    @Qualifier("allyRequestResourceService")
    private final RequestResourceService requestResourceService;
    @Autowired
    private final PlayerService playerService;
    @Autowired
    private final UserActivePlayerService userActivePlayerService;
    @Autowired
    private final WorldAdminAccessService worldService;
    @Autowired
    private final SecurityContextHolderService contextHolderService;

    private Timer timer;
    private TimerTask timerTask;
    private Long currentuserId;

    private final List<RequestResourceModel> filteredRequestResources = new ArrayList<>();

    public RequestResourceListController(PropertyBinder propertyBinder,
                                         BeanManager beanManager,
                                         @Qualifier("allyRequestResourceService") RequestResourceService requestResourceService,
                                         PlayerService playerService,
                                         UserActivePlayerService userActivePlayerService,
                                         WorldAdminAccessService worldService,
                                         SecurityContextHolderService contextHolderService) {
        this.propertyBinder = propertyBinder;
        this.beanManager = beanManager;
        this.requestResourceService = requestResourceService;
        this.playerService = playerService;
        this.userActivePlayerService = userActivePlayerService;
        this.worldService = worldService;
        this.contextHolderService = contextHolderService;
    }

    @DolphinModel
    private RequestResourceListWindowModel model;

    private class TimerTaskImpl extends TimerTask {

        public TimerTaskImpl(){
            filteredRequestResources.clear();
            filteredRequestResources.addAll(getRequestResourceList());
        }

        @Override
        public void run(){
            //model.requestsResourcesProperty().addAll(filteredRequestResources);
        }
    }

    @Async
    @Scheduled(initialDelay = 5000, fixedDelay = 5000)
    public void UpdateRequestsResourcesList() {
        //filteredRequestResources.clear();
        //filteredRequestResources.addAll(getRequestResourceList());
        //model.requestsResourcesProperty().clear();
        //model.requestsResourcesProperty().addAll(getRequestResourceList());
        model.setRequestsResources(getRequestResourceList());
    }

    private List<RequestResourceModel> getRequestResourceList() {
        final List<RequestResource> result = requestResourceService.getRequestsResources();
        return result.stream()
                .map(resource -> {
                    return convert(resource);
                }).collect(Collectors.toList());
    }

    @DolphinAction(Constants.NEW_REQUEST_EVENT)
    public void newRequest(){
        RequestResource requestResource = new RequestResource(-1L,
                userActivePlayerService.getActivePlayer(contextHolderService.getCurrentUser().getId()).getId(),
                model.newRequestProperty().get().getName(),
                model.newRequestProperty().get().getAmount());

        RequestResource added = requestResourceService.addRequestResource(requestResource);
        if (added != null) model.requestsResourcesProperty().add(convert(added));
    }

    /*@DolphinAction(Constants.LIST_CHANGE_EVENT)
    public void onListChanged(){
        List<RequestResourceModel> res = getRequestResourceList();
        for (RequestResourceModel requestResource: res
             ) {
            if (model.requestsResourcesProperty().contains(requestResource)) return;
            model.requestsResourcesProperty().add(requestResource);
        }
        //if (model.requestsResourcesProperty().equals(getRequestResourceList())) return;
        //model.requestsResourcesProperty().clear();
        //model.requestsResourcesProperty().addAll(getRequestResourceList());
    }*/

    @DolphinAction(Constants.SHOW_EVENT)
    public void onShow(){
        //model.requestsResourcesProperty().clear();
        //model.requestsResourcesProperty().addAll(getRequestResourceList());
        currentuserId = contextHolderService.getCurrentUser().getId();
        UpdateRequestsResourcesList();
        model.newRequestProperty().set(beanManager.create(RequestResourceModel.class));
        //timerTask = new TimerTaskImpl();
        //timer = new Timer();
        //timer.schedule(timerTask, 5000);
    }

    @PostConstruct
    public void init() {
        propertyBinder.bind(model.windowVisibleProperty(), Qualifiers.REQUEST_RESOURCE_WINDOW_VISIBLE_QUALIFIER);
        propertyBinder.bind(model.currentUserModelProperty(), Qualifiers.CURRENT_USER_MODEL_QUALIFIER);
        //model.selectedPlayerProperty().set(convert(userActivePlayerService.getActivePlayer(contextHolderService.getCurrentUser().getId())));


    }

    private RequestResourceModel convert(RequestResource requestResource) {
        final RequestResourceModel requestResourceModel = beanManager.create(RequestResourceModel.class);
        requestResourceModel.idProperty().set(requestResource.getId());
        requestResourceModel.playerProperty().set(
                convert(playerService.findPlayer(requestResource.getPlayerId()).orElseThrow(() -> new NotFoundException("cant find player"))));
        requestResourceModel.nameProperty().set(requestResource.getName());
        requestResourceModel.amountProperty().set(requestResource.getAmount());
        return requestResourceModel;
    }

    private RequestResource convert(RequestResourceModel requestResourceModel) {
        //final RequestResource requestResource =
        return new RequestResource(requestResourceModel.getId(),
                requestResourceModel.getPlayer().getId(),
                requestResourceModel.getName(),
                requestResourceModel.getAmount());

    }

    private PlayerModel convert(Player player) {
        final PlayerModel playerModel = beanManager.create(PlayerModel.class);
        playerModel.idProperty().set(player.getId());
        playerModel.userIdProperty().set(player.getUserId());
        playerModel.nicknameProperty().set(player.getNick());
        playerModel.worldProperty().set(convert(worldService.getWorld(player.getWorldId())));
        //playerModel.canNotPlayerCreateFactionProperty().set(false);
        return playerModel;
    }

    private WorldAdminModel convert(WorldAdminAccess world) {
        final WorldAdminModel worldModel = beanManager.create(WorldAdminModel.class);
        worldModel.idProperty().set(world.getId());
        worldModel.nameProperty().set(world.getName());
        return worldModel;
    }
}
