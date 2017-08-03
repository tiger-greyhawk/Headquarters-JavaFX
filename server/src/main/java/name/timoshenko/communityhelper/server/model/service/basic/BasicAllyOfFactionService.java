package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.domain.AllyOfFaction;
import name.timoshenko.communityhelper.server.model.repositories.AllyOfFactionRepository;
import name.timoshenko.communityhelper.server.model.service.AllyOfFactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BasicAllyOfFactionService implements AllyOfFactionService {

    @Autowired
    AllyOfFactionRepository allyOfFactionRepository;

    @Override
    public List<AllyOfFaction> findAllyFactions(Long factionId) {
        List<AllyOfFaction> allyFactions = allyOfFactionRepository.findByFirstFactionId(factionId);
        allyFactions.addAll(allyOfFactionRepository.findBySecondFactionId(factionId));
        return allyFactions;
    }

    @Override
    public AllyOfFaction save(AllyOfFaction allyOfFaction) {
        return allyOfFactionRepository.save(allyOfFaction);
    }

    @Override
    public boolean delete(AllyOfFaction allyOfFaction) {
        try {
            allyOfFactionRepository.delete(allyOfFaction);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
