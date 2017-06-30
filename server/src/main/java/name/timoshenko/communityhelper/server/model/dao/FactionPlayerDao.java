package name.timoshenko.communityhelper.server.model.dao;

import name.timoshenko.communityhelper.server.model.domain.FactionPlayer;

import java.util.List;

/**
 *
 */
public interface FactionPlayerDao extends Dao<FactionPlayer> {
    List<FactionPlayer> findByFactionId(long factionId);
}
