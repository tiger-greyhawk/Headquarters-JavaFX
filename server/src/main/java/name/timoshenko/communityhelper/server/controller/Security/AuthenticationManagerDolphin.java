package name.timoshenko.communityhelper.server.controller.Security;

import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.domain.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Component
public class AuthenticationManagerDolphin implements AuthenticationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationManagerDolphin.class);

    @Autowired
    UserDetailsServiceDolphin userDetailsService;

    public AuthenticationManagerDolphin(UserDetailsServiceDolphin userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    //static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        UserDetails userToAuth = userDetailsService.loadUserByUsername(auth.getName());
        if (userToAuth == null) throw new BadCredentialsException("Bad Credentials");
        if (userToAuth.getPassword().equals(auth.getCredentials()))
        {
            LOGGER.info("авторизовался пользователь "+ auth.getPrincipal().toString());
            /*for (UserRole role : userToAuth.getAuthorities()){
                AUTHORITIES.add(new SimpleGrantedAuthority(role.getName()));
            }*/
            return new UsernamePasswordAuthenticationToken(userToAuth.getUsername(),
                    userToAuth.getPassword(), userToAuth.getAuthorities());
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    /*@Override
    public boolean supports(Class authentication) {
        return authentication == AuthenticationDolphin.class;
    }*/

}