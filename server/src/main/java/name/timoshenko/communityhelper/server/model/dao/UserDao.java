package name.timoshenko.communityhelper.server.model.dao;

import name.timoshenko.communityhelper.server.model.domain.User;

import java.util.Optional;

/**
 *
 */
public interface UserDao extends Dao<User> {
    Optional<User> findByLogin(String login);
}
