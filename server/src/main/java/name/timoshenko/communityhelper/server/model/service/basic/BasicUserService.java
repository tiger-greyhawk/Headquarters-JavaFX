package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.controller.Security.UserDetailsServiceDolphin;
import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.repositories.UserRepository;
import name.timoshenko.communityhelper.server.model.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class BasicUserService implements UserService {

    private final UserRepository userRepository;


    public BasicUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }
}
