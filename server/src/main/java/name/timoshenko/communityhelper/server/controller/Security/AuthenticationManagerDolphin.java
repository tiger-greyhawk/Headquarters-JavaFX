package name.timoshenko.communityhelper.server.controller.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 *
 */


/***
 * На самом деле здесь надо использовать не UserDetailsService, а внедрять сюда провайдера(ов). Т.е. надо переделать на AuthenticationProviderDolphin
 * Смотрим описание структуры Spring Security. Наиболее понятно это написано в п. 10.4.1 книги Спринг в действии 3-е издание.
 */
@Component
public class AuthenticationManagerDolphin implements AuthenticationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationManagerDolphin.class);

    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationManagerDolphin(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        UserDetails userToAuth = userDetailsService.loadUserByUsername(auth.getName());
        if (userToAuth == null) throw new BadCredentialsException("Bad Credentials");
        if (userToAuth.getPassword().equals(auth.getCredentials()))
        {
            LOGGER.info("авторизовался пользователь "+ auth.getPrincipal().toString());
            return new UsernamePasswordAuthenticationToken((UserDetails)userToAuth,
                    userToAuth.getPassword(), userToAuth.getAuthorities());
        }
        throw new BadCredentialsException("Bad Credentials");
    }

}