package name.timoshenko.communityhelper.server.model.service;

import name.timoshenko.communityhelper.server.model.domain.User;

import java.util.Optional;

/**
 *
 */
public interface UserService {
    Optional<User> findUserByLogin(String login);
}
