package name.timoshenko.communityhelper.server.model.dao.dummy;

import name.timoshenko.communityhelper.server.model.dao.UserDao;
import name.timoshenko.communityhelper.server.model.domain.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
@Component
public class DummyUserDao implements UserDao {

    private final List<User> dummy = Arrays.asList(
            new User(1L, "User1", "123"),
            new User(2L, "User2", "123"),
            new User(3L, "User3", "123")
    );

    @Override
    public List<User> findAll() {
        return dummy;
    }

    @Override
    public Optional<User> findById(long id) {
        return dummy.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    @Override
    public List<User> findById(List<Long> ids) {
        return dummy.stream().filter(i -> ids.contains(i.getId())).collect(Collectors.toList());
    }

    @Override
    public User save(User object) {
        return null;
    }

    @Override
    public boolean exists(long id) {
        return dummy.stream().anyMatch(i -> i.getId() == id);
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return dummy.stream().filter(u -> u.getLogin().equals(login)).findAny();
        //return dummy.stream().filter(user -> user.getLogin().equals(login)).findFirst().get();
    }


    @Override
    public User findByLogin1(String login) {
        return dummy.stream().filter(user -> user.getLogin().equals(login)).findFirst().get();

    }
}
