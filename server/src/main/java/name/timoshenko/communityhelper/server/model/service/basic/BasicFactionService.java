package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.domain.Faction;
import name.timoshenko.communityhelper.server.model.repositories.FactionRepository;
import name.timoshenko.communityhelper.server.model.service.FactionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service("basicFactionService")
public class BasicFactionService implements FactionService {

    private final FactionRepository factionRepository;

    public BasicFactionService(FactionRepository factionRepository) {
        this.factionRepository = factionRepository;
    }

    @Override
    public List<Faction> getFactions(String pattern) {
        if (pattern.trim().isEmpty()) {
            return factionRepository.findAll();
        }
        return factionRepository.findByNameLike("%" + pattern + "%");
    }
}
