package name.timoshenko.communityhelper.server.controller;

import com.canoo.dolphin.BeanManager;
import com.canoo.platform.server.DolphinAction;
import com.canoo.platform.server.DolphinController;
import com.canoo.platform.server.DolphinModel;
import com.canoo.platform.server.binding.PropertyBinder;
import com.canoo.platform.server.event.DolphinEventBus;
import name.timoshenko.communityhelper.common.Constants;
import name.timoshenko.communityhelper.common.model.CurrentUserModel;
import name.timoshenko.communityhelper.common.model.FactionListWindowModel;
import name.timoshenko.communityhelper.common.model.MainWindowModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 *
 */
@DolphinController(Constants.MAIN_CONTROLLER_NAME)
public class MainController {

    private final BeanManager beanManager;
    private final PropertyBinder propertyBinder;
    private final DolphinEventBus eventBus;

    @DolphinModel
    private MainWindowModel model;


    @Autowired
    public MainController(BeanManager beanManager, PropertyBinder propertyBinder, DolphinEventBus eventBus) {
        this.beanManager = beanManager;
        this.propertyBinder = propertyBinder;
        this.eventBus = eventBus;
    }

    @PostConstruct
    public void init() {
        propertyBinder.bind(model.factionWinowVisibleProperty(), Qualifiers.FACTION_WINDOW_VISIBLE_QUALIFIER);
        propertyBinder.bind(model.currentUserModelProperty(), Qualifiers.CURRENT_USER_MODEL_QUALIFIER);
    }

}
