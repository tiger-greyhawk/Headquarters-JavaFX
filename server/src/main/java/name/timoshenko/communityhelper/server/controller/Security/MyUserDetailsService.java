package name.timoshenko.communityhelper.server.controller.Security;

import name.timoshenko.communityhelper.server.model.dao.dummy.DummyUserDao;
import name.timoshenko.communityhelper.server.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sun.management.Agent;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Tiger on 30.06.2017.
 */
public class MyUserDetailsService implements UserDetailsService {

    /*private static final Logger log = Logger
            .getLogger(MyUserDetailsService.class);*/
    private final DummyUserDao dummyUserDao;

    @Autowired
    MyUserDetailsService(DummyUserDao dummyUserDao) {
        this.dummyUserDao = dummyUserDao;
    }

    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        User user = dummyUserDao.findByLogin1(login);



        //throw new WebApplicationException(Response.Status.NOT_FOUND);


        //if (user == null) { throw  new WebApplicationException(Response.Status.NOT_FOUND);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        /*for (UserRole userRole : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getName()));
        }*/
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPasswordHash(), grantedAuthorities);
    }


}

