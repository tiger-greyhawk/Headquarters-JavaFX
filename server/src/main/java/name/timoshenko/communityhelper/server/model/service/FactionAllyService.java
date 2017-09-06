package name.timoshenko.communityhelper.server.model.service;

import name.timoshenko.communityhelper.server.model.domain.FactionAlly;

import java.util.List;

public interface FactionAllyService {
    List<FactionAlly> findFactionAllies(Long factionId);
    int checkBothRecordAlly(Long firstId, Long secondId);

    FactionAlly save(FactionAlly factionAlly);
    boolean delete(FactionAlly factionAlly);
}
