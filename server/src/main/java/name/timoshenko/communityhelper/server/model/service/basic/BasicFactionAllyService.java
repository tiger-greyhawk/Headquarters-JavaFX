package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.domain.FactionAlly;
import name.timoshenko.communityhelper.server.model.repositories.FactionAllyRepository;
import name.timoshenko.communityhelper.server.model.service.FactionAllyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BasicFactionAllyService implements FactionAllyService {

    @Autowired
    FactionAllyRepository factionAllyRepository;

    @Override
    public List<FactionAlly> findFactionAllies(Long factionId) {
        List<FactionAlly> allyFactions = factionAllyRepository.findByFirstFactionId(factionId);
        allyFactions.addAll(factionAllyRepository.findBySecondFactionId(factionId));
        return allyFactions;
    }

    @Override
    public int checkBothRecordAlly(Long firstId, Long secondId){
        FactionAlly first = factionAllyRepository.findByFirstFactionIdAndSecondFactionId(firstId, secondId);
        FactionAlly second = factionAllyRepository.findByFirstFactionIdAndSecondFactionId(secondId, firstId);
        if (first != null && second != null) return 2;
        else if (first != null && second == null) return 1;
        else if (first == null && second != null) return 0;
        else return -1;
    }

    @Override
    public FactionAlly save(FactionAlly factionAlly) {
            FactionAlly exist =
                    factionAllyRepository.findByFirstFactionIdAndSecondFactionId(
                            factionAlly.getFirstFactionId(),
                            factionAlly.getSecondFactionId());
            if (exist != null) {
                delete(exist);
                exist = null;
            }
        return factionAllyRepository.save(factionAlly);
    }

    @Override
    public boolean delete(FactionAlly factionAlly) {
        try {
            factionAllyRepository.delete(factionAlly);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
