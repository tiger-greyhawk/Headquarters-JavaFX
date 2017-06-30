package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.dao.UserDao;
import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 *
 */
@Component
public class BasicUserService implements UserService {

    private final UserDao userDao;

    public BasicUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> findUserById(long userId) {
        return userDao.findById(userId);
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return userDao.findByLogin(login);
    }
}
