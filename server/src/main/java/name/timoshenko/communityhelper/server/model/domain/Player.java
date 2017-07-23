package name.timoshenko.communityhelper.server.model.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 */
@Entity
@Table(name = "players")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Column(name = "user_id", nullable = false)
    private final Long userId;
    @Column(name = "nick", nullable = false)
    private final String nick;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "players")
    private User user;

    public Player() {
        this(null, null, null);
    }

    public Player(Long id, Long userId, String nick) {
        this.id = id;
        this.userId = userId;
        this.nick = nick;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getNick() {
        return nick;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", userId=" + userId +
                ", nick='" + nick + '\'' +
                '}';
    }
}
