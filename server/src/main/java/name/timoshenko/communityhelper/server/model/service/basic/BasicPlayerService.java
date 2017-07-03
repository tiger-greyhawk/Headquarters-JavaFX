package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.repositories.PlayerRepository;
import name.timoshenko.communityhelper.server.model.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service("basicPlayerService")
public class BasicPlayerService implements PlayerService {

    private final PlayerRepository playerRepository;

    public BasicPlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Optional<Player> findPlayer(long id) {
        return Optional.ofNullable(playerRepository.findOne(id));
    }

    @Override
    public List<Player> getPlayers(List<Long> id) {
        return playerRepository.findAll(id);
    }
}
