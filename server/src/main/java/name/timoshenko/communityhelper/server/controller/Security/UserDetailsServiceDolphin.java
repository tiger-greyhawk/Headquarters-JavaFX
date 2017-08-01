package name.timoshenko.communityhelper.server.controller.Security;

import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserDetailsServiceDolphin implements UserDetailsService {

    /*private static final Logger log = Logger
            .getLogger(UserDetailsServiceDolphin.class);*/

    @Autowired
    private final UserRepository userRepository;

    UserDetailsServiceDolphin(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("No User With Login \"" + login + "\" Was Found"));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        //for (UserRole userRole : user.getRoles()) {
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        //}
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPasswordHash(), grantedAuthorities);
        //return user;

    }
}

