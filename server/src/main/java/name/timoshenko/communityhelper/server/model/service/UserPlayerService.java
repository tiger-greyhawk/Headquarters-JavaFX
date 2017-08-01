package name.timoshenko.communityhelper.server.model.service;

import name.timoshenko.communityhelper.server.model.domain.UserPlayer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public interface UserPlayerService {
    /*TODO object or id
     * Надо подумать, надо ли мне возвращать объект (пару id), или возвращать только один парный id вида Long
     */

    /*TODO именование методов
    еще раз обратить внимание на что принимают/ что возвращают
     */
    //Optional<UserPlayer> findPlayerByUserId(Long userId);
    List<UserPlayer> findByUserId(Long userId);
    List<Long> findIdsByUserId(Long userId);
    Optional<UserPlayer> findByPlayerId(Long playerId);
    UserPlayer save(UserPlayer userPlayer);
}
