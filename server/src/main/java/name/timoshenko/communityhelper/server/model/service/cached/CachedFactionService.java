package name.timoshenko.communityhelper.server.model.service.cached;

import name.timoshenko.communityhelper.server.model.domain.Faction;
import name.timoshenko.communityhelper.server.model.service.FactionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service("cachedFactionService")
public class CachedFactionService implements FactionService {

    private final FactionService source;
    private final Map<String, List<Faction>> factionsCache = new HashMap<>();

    public CachedFactionService(@Qualifier("basicFactionService") FactionService source) {
        this.source = source;
    }

    @Override
    public synchronized List<Faction> getFactions(String pattern) {
        if (factionsCache.containsKey(pattern)) {
            return factionsCache.get(pattern);
        }
        final List<Faction> factions = source.getFactions(pattern);
        factionsCache.put(pattern, factions);
        return factions;
    }
}
