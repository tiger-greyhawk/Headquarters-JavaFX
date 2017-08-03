package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.domain.Faction;
import name.timoshenko.communityhelper.server.model.repositories.FactionRepository;
import name.timoshenko.communityhelper.server.model.service.FactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service("basicFactionService")
@Transactional(propagation = Propagation.REQUIRED)
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

    @Override
    public Faction findFactionByOwnerId(Long ownerId) {
        return factionRepository.findByOwnerId(ownerId);
    }

    @Override
    public Optional<Faction> getFactionById(Long factionId){
        return Optional.ofNullable(factionRepository.findOne(factionId));
    }

    @Override
    public Faction createFaction(Faction faction) {
        if (faction == null) return null;
        return  factionRepository.save(faction);
    }

    @Override
    public void deleteFaction(Long factionId) {
        factionRepository.delete(factionId);
    }
}
