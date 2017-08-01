package name.timoshenko.communityhelper.server.model.repositories;

import name.timoshenko.communityhelper.server.model.domain.UserPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Repository
public interface UserPlayerRepository extends JpaRepository<UserPlayer, Long> {

    List<UserPlayer> findByUserId(Long userId);
    Optional<UserPlayer> findByPlayerId(Long playerId);
}
