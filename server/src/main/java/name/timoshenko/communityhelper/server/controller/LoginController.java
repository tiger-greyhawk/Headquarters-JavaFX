package name.timoshenko.communityhelper.server.controller;

import com.canoo.dolphin.BeanManager;
import com.canoo.platform.server.DolphinAction;
import com.canoo.platform.server.DolphinController;
import com.canoo.platform.server.DolphinModel;
import com.canoo.platform.server.binding.PropertyBinder;
import name.timoshenko.communityhelper.common.Constants;
import name.timoshenko.communityhelper.common.model.CurrentUserModel;
import name.timoshenko.communityhelper.common.model.LoginWindowModel;
import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.service.AuthService;
import name.timoshenko.communityhelper.server.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 *
 */
@DolphinController(Constants.LOGIN_CONTROLLER_NAME)
public class LoginController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final BeanManager beanManager;
    private final PropertyBinder propertyBinder;
    private final UserService userService;

    @DolphinModel
    private LoginWindowModel loginWindowModel;

    @Autowired
    public LoginController(AuthService authService,
                           AuthenticationManager authenticationManagerDolphin, BeanManager beanManager,
                           PropertyBinder propertyBinder, UserService userService) {
        this.authService = authService;
        this.authenticationManager = authenticationManagerDolphin;
        this.beanManager = beanManager;
        this.propertyBinder = propertyBinder;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        loginWindowModel.currentUserModelProperty().set(beanManager.create(CurrentUserModel.class));
        propertyBinder.bind(loginWindowModel.currentUserModelProperty(), Qualifiers.CURRENT_USER_MODEL_QUALIFIER);
        loginWindowModel.currentUserModelProperty().get().loggedInProperty().set(false);
    }

    @DolphinAction(Constants.LOGIN_EVENT)
    public void onLoginEvent() {
        final String login = loginWindowModel.currentUserModelProperty().get().loginProperty().get();
        final boolean authenticated = authService.isAuthenticated(
                login,
                loginWindowModel.currentUserModelProperty().get().passwordProperty().get());

        if (authenticated) {
            final User user = userService.findUserByLogin(login).orElseThrow(() -> new RuntimeException("User Disappeared During Login"));
            loginWindowModel.currentUserModelProperty().get().loggedInProperty().set(true);
            loginWindowModel.currentUserModelProperty().get().userIdProperty().set(user.getId());
            loginWindowModel.currentUserModelProperty().get().loginProperty().set(user.getLogin());
            loginWindowModel.currentUserModelProperty().get().passwordProperty().set(null);
        }
    }

}
