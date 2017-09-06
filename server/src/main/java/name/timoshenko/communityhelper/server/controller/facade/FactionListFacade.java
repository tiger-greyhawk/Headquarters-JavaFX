package name.timoshenko.communityhelper.server.controller.facade;

import name.timoshenko.communityhelper.server.model.domain.Faction;

import java.util.List;
import java.util.Optional;

public interface FactionListFacade {
    boolean isCurrentUserOwnsCurrentFaction(Long factionOwnerId);
    List<Faction> getFactions(final String filter);
    Faction createFaction(String factionName, String factionSlogan, Long worldId);
    Optional<String> getAlliedStatus(Faction factionToCheck);
    Optional<Faction> getMyCurrentFaction();
}
