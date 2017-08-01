package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.domain.UserPlayer;
import name.timoshenko.communityhelper.server.model.repositories.UserPlayerRepository;
import name.timoshenko.communityhelper.server.model.service.UserPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 *
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class BasicUserPlayerService implements UserPlayerService {

    @Autowired
    UserPlayerRepository userPlayerRepository;

    @Override
    public List<UserPlayer> findByUserId(Long userId) {
        return userPlayerRepository.findByUserId(userId);
    }

    @Override
    public Optional<UserPlayer> findByPlayerId(Long playerId) {
        return userPlayerRepository.findByPlayerId(playerId);
    }

    @Override
    public List<Long> findIdsByUserId(Long userId) {
        return userPlayerRepository.findByUserId(userId).stream().map(userPlayer -> userPlayer.getPlayerId()).collect(Collectors.toList());
    }

    @Override
    public UserPlayer save(UserPlayer userPlayer) {
        return userPlayerRepository.save(userPlayer);
    }
}
