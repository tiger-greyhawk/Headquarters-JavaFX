package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.service.AuthService;
import name.timoshenko.communityhelper.server.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class BasicAuthService implements AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public BasicAuthService(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public boolean isAuthenticated(String login, String passwordHash) {
        boolean equ = userService.findUserByLogin(login).map(User::getPasswordHash).map(s -> s.equals(passwordHash)).orElse(false);
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(login, passwordHash);
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            return true;
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return false;
        }
    }
}