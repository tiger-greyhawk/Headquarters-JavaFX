package name.timoshenko.communityhelper.server.controller;

import com.canoo.dolphin.BeanManager;
import com.canoo.platform.server.DolphinAction;
import com.canoo.platform.server.DolphinController;
import com.canoo.platform.server.DolphinModel;
import com.canoo.platform.server.event.DolphinEventBus;
import name.timoshenko.communityhelper.common.Constants;
import name.timoshenko.communityhelper.common.model.CurrentUserModel;
import name.timoshenko.communityhelper.common.model.FactionListModel;
import name.timoshenko.communityhelper.common.model.MainModel;
import name.timoshenko.communityhelper.server.model.domain.Faction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;

/**
 *
 */
@DolphinController(Constants.MAIN_CONTROLLER_NAME)
public class MainController {

    private final BeanManager beanManager;
    private final DolphinEventBus eventBus;

    @DolphinModel
    private MainModel model;


    @Autowired
    public MainController(BeanManager beanManager, DolphinEventBus eventBus) {
        this.beanManager = beanManager;
        this.eventBus = eventBus;
    }

    @PostConstruct
    public void init() {
        model.currentUserModelProperty().set(beanManager.create(CurrentUserModel.class));
        model.currentUserModelProperty().get().loggedInProperty().set(false);



        eventBus.subscribe(EventTopics.LOGIN_TOPIC, m -> {
            System.err.println("LOGGED IN: " + m.getData().loggedInProperty().get());
            model.currentUserModelProperty().get().loggedInProperty().set(m.getData().loggedInProperty().get());
            model.currentUserModelProperty().get().userIdProperty().set(m.getData().userIdProperty().get());
            model.currentUserModelProperty().get().loginProperty().set(m.getData().loginProperty().get());
            //model.factionsProperty().setAll(getFactionList(""));
        });

        /*eventBus.subscribe(EventTopics.FACTION_LIST_SHOW_TOPIC, m -> {
            System.err.println("Try to show FactionListView: " );

        });*/
    }

    @DolphinAction(Constants.FACTION_LIST_SHOW_EVENT)
    public void onFactionListShowEvent() {
        eventBus.publish(EventTopics.FACTION_LIST_SHOW_TOPIC, new FactionListModel());

    }

    /*@DolphinAction
    public void onFactionListShow()
    {

    }*/
}
