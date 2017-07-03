package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.repositories.UserRepository;
import name.timoshenko.communityhelper.server.model.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 *
 */
@Component
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
