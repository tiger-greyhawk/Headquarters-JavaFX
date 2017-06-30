package name.timoshenko.communityhelper.server.model.dao.rest;

import name.timoshenko.communityhelper.server.model.dao.PlayerDao;
import name.timoshenko.communityhelper.server.model.domain.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service("restPlayerDao")
public class RestPlayerDao implements PlayerDao {
    @Override
    public List<Player> findAll() {
        return null;
    }

    @Override
    public Optional<Player> findById(long id) {
        return null;
    }

    @Override
    public List<Player> findById(List<Long> ids) {
        return null;
    }

    @Override
    public Player save(Player object) {
        return null;
    }

    @Override
    public boolean exists(long id) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<Player> findByUserId(long userId) {
        return null;
    }
}
