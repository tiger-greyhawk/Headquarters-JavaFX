package name.timoshenko.communityhelper.server.model.dao;

import name.timoshenko.communityhelper.server.model.domain.Player;

import java.util.List;

/**
 *
 */
public interface PlayerDao extends Dao<Player> {
    List<Player> findByUserId(long userId);
}
