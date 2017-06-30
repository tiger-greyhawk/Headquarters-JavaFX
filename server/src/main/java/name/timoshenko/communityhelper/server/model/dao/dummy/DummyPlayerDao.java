package name.timoshenko.communityhelper.server.model.dao.dummy;

import name.timoshenko.communityhelper.server.model.dao.PlayerDao;
import name.timoshenko.communityhelper.server.model.domain.Player;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
@Service("dummyPlayerDao")
public class DummyPlayerDao implements PlayerDao {

    private final List<Player> dummy = Arrays.asList(
            new Player(1L, 1L, "Player 1"),
            new Player(2L, 1L, "Player 2"),
            new Player(3L, 2L, "Player 3"),
            new Player(4L, 2L, "Player 4"),
            new Player(5L, 3L, "Player 5"),
            new Player(6L, 4L, "Player 6"),
            new Player(7L, 5L, "Player 7"),
            new Player(8L, 6L, "Player 8")
    );

    @Override
    public List<Player> findAll() {
        return dummy;
    }

    @Override
    public Optional<Player> findById(long id) {
        return dummy.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    @Override
    public List<Player> findById(List<Long> ids) {
        return dummy.stream().filter(i -> ids.contains(i.getId())).collect(Collectors.toList());
    }

    @Override
    public Player save(Player object) {
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
    public List<Player> findByUserId(long userId) {
        return dummy.stream().filter(p -> p.getId().equals(userId))
                .collect(Collectors.toList());
    }
}
