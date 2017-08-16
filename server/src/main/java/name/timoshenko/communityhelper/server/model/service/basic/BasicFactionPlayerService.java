package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.domain.FactionPlayer;
import name.timoshenko.communityhelper.server.model.repositories.FactionPlayerRepository;
import name.timoshenko.communityhelper.server.model.service.FactionPlayerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service("basicFactionPlayerService")
public class BasicFactionPlayerService implements FactionPlayerService {

    private final FactionPlayerRepository factionPlayerRepository;

    public BasicFactionPlayerService(FactionPlayerRepository factionPlayerRepository) {
        this.factionPlayerRepository = factionPlayerRepository;
    }

    @Override
    public List<Long> findPlayersByFactionId(Long factionId) {
        return factionPlayerRepository.findByFactionId(factionId)
                .stream()
                .map(FactionPlayer::getPlayerId)
                .collect(Collectors.toList());
    }

    @Override
    public Long findFactionByPlayerId(Long playerId) {
        return factionPlayerRepository.findByPlayerId(playerId).getFactionId();
    }
}
