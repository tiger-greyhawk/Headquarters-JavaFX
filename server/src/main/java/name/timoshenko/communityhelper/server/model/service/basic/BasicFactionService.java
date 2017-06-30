package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.dao.FactionDao;
import name.timoshenko.communityhelper.server.model.domain.Faction;
import name.timoshenko.communityhelper.server.model.service.FactionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.regex.PatternSyntaxException;

/**
 *
 */
@Service("basicFactionService")
public class BasicFactionService implements FactionService {

    private final FactionDao factionDao;

    public BasicFactionService(@Qualifier("dummyFactionDao") FactionDao factionDao) {
        this.factionDao = factionDao;
    }

    @Override
    public List<Faction> getFactions(String pattern) {
        if (pattern.trim().isEmpty()) {
            return factionDao.findAll();
        }
        try {
            return factionDao.findByPattern(pattern);
        } catch (PatternSyntaxException e) {
            return Collections.emptyList();
        }
    }
}
