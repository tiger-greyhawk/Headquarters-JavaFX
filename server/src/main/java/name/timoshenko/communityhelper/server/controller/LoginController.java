package name.timoshenko.communityhelper.server.controller;

import com.canoo.platform.server.DolphinAction;
import com.canoo.platform.server.DolphinController;
import com.canoo.platform.server.DolphinModel;
import com.canoo.platform.server.event.DolphinEventBus;
import name.timoshenko.communityhelper.common.Constants;
import name.timoshenko.communityhelper.common.model.CurrentUserModel;
import name.timoshenko.communityhelper.server.model.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import javax.annotation.PostConstruct;

/**
 *
 */
@DolphinController(Constants.LOGIN_CONTROLLER_NAME)
public class LoginController {

    private final DolphinEventBus eventBus;
    private final AuthService authService;
    //private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    //private final AuthenticationDolphin authenticationDolphin;

    @DolphinModel
    private CurrentUserModel currentUserModel;

    @Autowired
    //public LoginController(DolphinEventBus eventBus, AuthService authService, AuthenticationProviderDolphin authenticationProviderDolphin, AuthenticationDolphin authenticationDolphin) {
    //public LoginController(DolphinEventBus eventBus, AuthService authService, AuthenticationManager authenticationManagerDolphin, CurrentUserModel currentUserModel) {
    public LoginController(DolphinEventBus eventBus, AuthService authService, AuthenticationManager authenticationManagerDolphin) {
        this.eventBus = eventBus;
        this.authService = authService;
        this.authenticationManager = authenticationManagerDolphin;
        //this.currentUserModel = currentUserModel;
        //this.authenticationProviderDolphin = authenticationProviderDolphin;
        //this.authenticationDolphin = authenticationDolphin;
    }

    @PostConstruct
    public void init() {
        currentUserModel.loggedInProperty().set(false);

    }

    @DolphinAction(Constants.LOGIN_EVENT)
    public void onLoginEvent() {
        //AuthenticationDolphin authenticationDolphin = new AuthenticationDolphin(currentUserModel);
        //authenticationDolphin.currentUserModel = currentUserModel;
        //authenticationProviderDolphin.authenticate(authenticationDolphin);
        //SecurityContextHolder.createEmptyContext().setAuthentication(authenticationDolphin);
        //SecurityContextHolder.getContext().setAuthentication(authenticationDolphin);
        //Object pr = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Authentication auth = getAuthentication();
        //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //SecurityContextHolder.getContext().setAuthentication(auth);
        //UserDetails userDetails = userDetailsService.loadUserByUsername(currentUserModel.loginProperty().get());

        currentUserModel.loggedInProperty().set(
                authService.isAuthenticated(
                        currentUserModel.loginProperty().get(),
                        currentUserModel.passwordProperty().get()));
        currentUserModel.userIdProperty().set(1L);

        eventBus.publish(EventTopics.LOGIN_TOPIC, currentUserModel);

    }

}
