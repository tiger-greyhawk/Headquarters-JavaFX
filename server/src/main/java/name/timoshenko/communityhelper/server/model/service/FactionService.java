package name.timoshenko.communityhelper.server.model.service;

import name.timoshenko.communityhelper.server.model.domain.Faction;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    Faction createFaction(Faction faction);

    @PreAuthorize("hasRole('ROLE_USER')")
    void deleteFaction(Long factionId);
    Optional<Faction> getFactionById(Long factionId);
}
