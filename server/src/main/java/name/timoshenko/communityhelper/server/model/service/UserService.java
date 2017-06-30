package name.timoshenko.communityhelper.server.model.service;

import name.timoshenko.communityhelper.server.model.domain.User;

import java.util.Optional;

/**
 *
 */
public interface UserService {
    Optional<User> findUserById(long userId);
    Optional<User> findUserByLogin(String login);
}
