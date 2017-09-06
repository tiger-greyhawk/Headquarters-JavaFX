package name.timoshenko.communityhelper.server.model.domain;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Column(name = "user_id", nullable = false)
    private final Long userId;
    @Column(name = "world_id", nullable = false)
    private final Long worldId;
    @Column(name = "nick", nullable = false)
    private final String nick;


    public Player() {
        this(null, null, null, null);
    }

    public Player(Long id, Long userId, Long worldId, String nick) {
        this.id = id;
        this.userId = userId;
        this.worldId = worldId;
        this.nick = nick;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getWorldId() {
        return worldId;
    }

    public String getNick() {
        return nick;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", userId=" + userId +
                ", worldId=" + worldId +
                ", nick='" + nick + '\'' +
                '}';
    }
}
