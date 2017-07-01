package name.timoshenko.communityhelper.server.controller.Security;

import name.timoshenko.communityhelper.server.model.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Created by Tiger on 30.06.2017.
 */
@Component
public class AuthenticationManagerDolphin implements AuthenticationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationManagerDolphin.class);

    private final UserDetailsService userDetailsService;

    public AuthenticationManagerDolphin(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

    static {
        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        //if (auth.getName().equals(auth.getCredentials()))
        //if (auth.getName() != null)
        UserDetails userToAuth = userDetailsService.loadUserByUsername(auth.getName());
        if (userToAuth.getPassword().equals(auth.getCredentials()))
        {
            LOGGER.info("авторизовался пользователь "+ auth.getPrincipal().toString());
            return new UsernamePasswordAuthenticationToken(auth.getName(),
                    auth.getCredentials(), AUTHORITIES);
        }
        throw new BadCredentialsException("Bad Credentials");
    }


    /*
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //AuthenticationDolphin authenticationDolphin = (AuthenticationDolphin) authentication;

        String name = (String) authentication.getPrincipal();
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        if (userDetails == null) {
            throw new UsernameNotFoundException("Unknown name");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        authentication.setAuthenticated(true);
        //authentication.setDetails(userDetails);

        return authentication;
    }*/

    /*@Override
    public boolean supports(Class authentication) {
        return authentication == AuthenticationDolphin.class;
    }*/

}