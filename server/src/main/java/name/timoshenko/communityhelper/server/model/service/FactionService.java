package name.timoshenko.communityhelper.server.model.service;

import name.timoshenko.communityhelper.server.model.domain.Faction;

import java.util.Collection;
import java.util.List;

/**
 *
 */
public interface FactionService {
    /**
     * Retrieves a collection of fractions that match specified criteria.
     * <p/>
     * If pattern is empty, all records are returned.
     *
     * @param pattern pattern to match, if pattern is empty, a full list is returned
     * @return a {@link Collection} of {@link Faction}s that match specified pattern
     */
    List<Faction> getFactions(String pattern);
}
