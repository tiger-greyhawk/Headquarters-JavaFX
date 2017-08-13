package name.timoshenko.communityhelper.server.controller.Security;

import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class SecurityContextHolderServiceImpl implements SecurityContextHolderService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;


    @Override
    public SecurityContext getContext(){
        return SecurityContextHolder.getContext();
    }

    @Override
    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * Возвращает из контекста текущего пользователя или пользователя anonymousUser с id=0 если пользователь не залогинен.
     * @return
     */
    @Override
    public User getCurrentUser(){
        SimpleGrantedAuthority roleAnonymous = new SimpleGrantedAuthority("ROLE_ANONYMOUS");
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(roleAnonymous))
            return new User(0L, "anonymousUser", "");
        UserDetails userDetails = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return userService.findUserByLogin(userDetails.getUsername()).orElseThrow(() -> new NotFoundException("User not found"));
    }
}
