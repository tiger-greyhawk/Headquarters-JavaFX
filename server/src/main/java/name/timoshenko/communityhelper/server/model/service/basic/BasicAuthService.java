package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.service.AuthService;
import name.timoshenko.communityhelper.server.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 *
 */
@Service
public class BasicAuthService implements AuthService {

    private final UserService userService;

    @Autowired
    public BasicAuthService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isAuthenticated(String login, String passwordHash) {
        return userService.findUserByLogin(login)
                .map(User::getPasswordHash)
                .map(s -> s.equals(passwordHash))
                .orElse(false);
    }
}
