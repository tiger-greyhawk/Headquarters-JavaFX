package name.timoshenko.communityhelper.server.model.repositories;

import name.timoshenko.communityhelper.server.model.domain.UserActivePlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 */
@Repository
public interface UserActivePlayerRepository extends JpaRepository<UserActivePlayer, Long> {
    Optional<UserActivePlayer> findByUserId(Long userId);
    Optional<UserActivePlayer> findByActivePlayerId(Long activePlayerId);
}
