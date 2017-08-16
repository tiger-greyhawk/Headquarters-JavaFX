package name.timoshenko.communityhelper.server.controller.service;

import name.timoshenko.communityhelper.server.model.domain.Faction;

import java.util.List;

public interface FactionListService {
    public boolean isCurrentUserOwnsCurrentFaction(Long factionOwnerId);
    public List<Faction> getFactions(final String filter);
    public String getAlliedStatus(Faction factionToCheck);
    public Faction getMyCurrentFaction();
}
