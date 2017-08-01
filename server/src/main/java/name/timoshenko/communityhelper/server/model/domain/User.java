package name.timoshenko.communityhelper.server.model.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Entity
@Transactional
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Column(name = "login", nullable = false, unique = true)
    private final String login;
    @Column(name = "password", nullable = false)
    private final String passwordHash;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity=UserRole.class)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    //private Collection<? extends GrantedAuthority> roles;
    private Set<UserRole> roles;

    public User() {
        this(null, null, null);
    }

    public User(Long id, String login, String passwordHash) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> _grntdAuths = new HashSet<GrantedAuthority>();

        //List<UserRole> _roles = null;

        //if (user!=null) {
        //    _roles = user.getRoles();
        //}

        //if (roles!=null) {
            for (UserRole userRole : roles) {
                _grntdAuths.add(new SimpleGrantedAuthority(userRole.getName()));
            }
        //}

        return _grntdAuths;
    }
    /*public Set<UserRole> getAuthorities() {
        return roles;
    }*/

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return login;
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
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", roles=" + roles.toString() +
                '}';
    }
}
