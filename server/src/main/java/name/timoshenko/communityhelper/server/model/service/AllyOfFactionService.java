package name.timoshenko.communityhelper.server.model.service;

import name.timoshenko.communityhelper.server.model.domain.AllyOfFaction;

import java.util.List;

public interface AllyOfFactionService {
    List<AllyOfFaction> findAllyFactions(Long factionId);

    AllyOfFaction save(AllyOfFaction allyOfFaction);
    boolean delete(AllyOfFaction allyOfFaction);
}
