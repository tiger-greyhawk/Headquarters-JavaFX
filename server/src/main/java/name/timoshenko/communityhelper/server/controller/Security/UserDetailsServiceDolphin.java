package name.timoshenko.communityhelper.server.controller.Security;

import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.domain.UserRole;
import name.timoshenko.communityhelper.server.model.repositories.UserRepository;
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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserDetailsServiceDolphin implements UserDetailsService {

    /*private static final Logger log = Logger
            .getLogger(UserDetailsServiceDolphin.class);*/

    @Autowired
    UserRepository userRepository;
    //private final UserRepository userRepository;
    @Autowired
    UserDetailsServiceDolphin() {
        //this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        User user = userRepository.findOneByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("No User With Login \"" + login + "\" Was Found"));

        //Collection<UserRole> grantedAuthorities = new HashSet<>();
        //SimpleGrantedAuthority authority = user.getAuthorities();
        Collection<GrantedAuthority>  grantedAuthorities = new HashSet<>();
        for (UserRole userRole : user.getAuthorities()) {
        grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getName()));
        }


        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPasswordHash(), grantedAuthorities);
        //return user;
    }


}

