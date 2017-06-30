package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.dao.PlayerDao;
import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.service.PlayerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service("basicPlayerService")
public class BasicPlayerService implements PlayerService {

    private final PlayerDao playerDao;

    public BasicPlayerService(@Qualifier("dummyPlayerDao") PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public Optional<Player> findPlayer(long id) {
        return playerDao.findById(id);
    }

    @Override
    public List<Player> findPlayersByUserId(long userId) {
        return playerDao.findByUserId(userId);
    }

    @Override
    public List<Player> getPlayers(List<Long> id) {
        return playerDao.findById(id);
    }
}
