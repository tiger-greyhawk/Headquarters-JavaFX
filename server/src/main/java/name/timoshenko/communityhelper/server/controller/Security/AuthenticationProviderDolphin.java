package name.timoshenko.communityhelper.server.controller.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

//import static name.timoshenko.communityhelper.server.controller.Security.AuthenticationManagerDolphin.AUTHORITIES;

/**
 * Сюда мы пока не заходим. Надо его передавать в AuthenticationManager
 */
@Component
public class AuthenticationProviderDolphin implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationManagerDolphin.class);

    @Autowired
    UserDetailsService userDetailsService;


    /*public AuthenticationProviderDolphin(UserDetailsServiceDolphin userDetailsService) {
        this.userDetailsService = userDetailsService;
    }*/

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        UserDetails userToAuth = userDetailsService.loadUserByUsername(auth.getName());
        if (userToAuth == null) throw new BadCredentialsException("Bad Credentials");
        if (userToAuth.getPassword().equals(auth.getCredentials()))
        {
            LOGGER.info("авторизовался пользователь "+ auth.getPrincipal().toString());
            return new UsernamePasswordAuthenticationToken(auth.getName(),
                    auth.getCredentials(), userToAuth.getAuthorities());
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
