package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.domain.Faction;
import name.timoshenko.communityhelper.server.model.domain.FactionPlayer;
import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.repositories.FactionPlayerRepository;
import name.timoshenko.communityhelper.server.model.service.FactionPlayerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public List<Long> findPlayerIdsByFactionId(Long factionId) {
        return factionPlayerRepository.findByFactionId(factionId)
                .stream()
                .map(FactionPlayer::getPlayerId)
                .collect(Collectors.toList());
    }

    @Override
    public List<FactionPlayer> findFactionPlayersByFactionId(Long factionId){
        return factionPlayerRepository.findByFactionId(factionId);
    }

    @Override
    public Optional<Long> findFactionByPlayerId(Long playerId) {
        Optional<FactionPlayer> result = factionPlayerRepository.findByPlayerId(playerId);
        if (!result.isPresent()) return Optional.empty();
        return Optional.of(result.get().getFactionId());

    }

    @Override
    public FactionPlayer addPlayerToFaction(Player player, Faction faction, Boolean invited, Boolean confirmed) {
        return factionPlayerRepository.save(new FactionPlayer(-1L, faction.getId(), player.getId(), invited, confirmed));
    }

    @Override
    public Boolean deletePlayerFromFaction(Long factionId, Long playerId){
        Optional<FactionPlayer> toDelete = factionPlayerRepository.findByPlayerId(playerId);
        if (toDelete.isPresent()) {
            if (toDelete.get().getFactionId().equals(factionId)) {
                factionPlayerRepository.delete(toDelete.get().getId());
                return true;
            }
            return false;
        }
        else return false;
    }

    @Override
    public Boolean deletePlayersFromFaction(Long factionId) {
        List<FactionPlayer> toDelete = factionPlayerRepository.findByFactionId(factionId);
        factionPlayerRepository.deleteInBatch(toDelete);
        return true;
    }
}
