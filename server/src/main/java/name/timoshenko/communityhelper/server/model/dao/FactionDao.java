package name.timoshenko.communityhelper.server.model.dao;

import name.timoshenko.communityhelper.server.model.domain.Faction;

import java.util.List;

/**
 *
 */
public interface FactionDao extends Dao<Faction> {

    List<Faction> findByPattern(String name);

}
