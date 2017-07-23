package name.timoshenko.communityhelper.server.model.domain;

import name.timoshenko.communityhelper.server.model.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */

@Table(name = "users")
public class UserDetailsImplementaionWithRoles implements UserDetails {

    @Autowired
    UserRoleService userRoleService;

    private User user;
    //private Collection<? extends GrantedAuthority> roles;
    //private Set<UserRole> roles;


    public UserDetailsImplementaionWithRoles(UserRoleService userRoleService, User user) {
        this.userRoleService = userRoleService;
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> _grntdAuths = new HashSet<GrantedAuthority>();

        List<UserRole> _roles = null;


        if (user!=null) {
            //_roles = user.getRoles();
            _roles = userRoleService.FindUserRolesByUserId(user.getId());
        }

        if (_roles!=null) {
            for (UserRole userRole : _roles) {
                _grntdAuths.add(new SimpleGrantedAuthority(userRole.getName()));
            }
        }

        return _grntdAuths;
    }

    public Long getId() {
        return user.getId();
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String toString() {
        return "UserDetailsImplementaionWithRoles{" +
                "user=" + user +
                '}';
    }
}