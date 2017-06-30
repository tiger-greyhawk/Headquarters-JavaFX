package name.timoshenko.communityhelper.server.controller;

import com.canoo.platform.server.DolphinAction;
import com.canoo.platform.server.DolphinController;
import com.canoo.platform.server.DolphinModel;
import com.canoo.platform.server.event.DolphinEventBus;
import name.timoshenko.communityhelper.common.Constants;
import name.timoshenko.communityhelper.common.model.CurrentUserModel;
import name.timoshenko.communityhelper.server.model.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 *
 */
@DolphinController(Constants.LOGIN_CONTROLLER_NAME)
public class LoginController {

    private final DolphinEventBus eventBus;
    private final AuthService authService;

    @DolphinModel
    private CurrentUserModel currentUserModel;

    @Autowired
    public LoginController(DolphinEventBus eventBus, AuthService authService) {
        this.eventBus = eventBus;
        this.authService = authService;
    }

    @PostConstruct
    public void init() {
        currentUserModel.loggedInProperty().set(false);

    }

    @DolphinAction(Constants.LOGIN_EVENT)
    public void onLoginEvent() {
        currentUserModel.loggedInProperty().set(
                authService.isAuthenticated(
                        currentUserModel.loginProperty().get(),
                        currentUserModel.passwordProperty().get()));
        currentUserModel.userIdProperty().set(1L);
        eventBus.publish(EventTopics.LOGIN_TOPIC, currentUserModel);
    }
}
