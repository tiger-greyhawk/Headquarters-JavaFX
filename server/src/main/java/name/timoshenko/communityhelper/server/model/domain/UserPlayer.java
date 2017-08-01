package name.timoshenko.communityhelper.server.model.domain;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "user_player")
public class UserPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "player_id", nullable = false)
    private Long playerId;

    public UserPlayer() {this(0L,0L,0L);
    }

    public UserPlayer(Long id, Long userId, Long playerId) {
        this.id = id;
        this.userId = userId;
        this.playerId = playerId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    @Override
    public String toString() {
        return "UserPlayer{" +
                "id=" + id +
                ", userId=" + userId +
                ", playerId=" + playerId +
                '}';
    }
}
