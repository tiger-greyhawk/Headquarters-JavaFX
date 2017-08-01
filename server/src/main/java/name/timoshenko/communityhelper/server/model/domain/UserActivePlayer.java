package name.timoshenko.communityhelper.server.model.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 */
@Entity
@Table(name = "user_active_player")
public class UserActivePlayer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "active_player_id", nullable = false)
    private Long activePlayerId;

    public UserActivePlayer() { this(0L,0L,0L);
    }

    public UserActivePlayer(Long id, Long userId, Long activePlayerId) {
        this.id = id;
        this.userId = userId;
        this.activePlayerId = activePlayerId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getActivePlayerId() {
        return activePlayerId;
    }

    @Override
    public String toString() {
        return "UserActivePlayer{" +
                "id=" + id +
                ", userId=" + userId +
                ", activePlayerId=" + activePlayerId +
                '}';
    }
}
