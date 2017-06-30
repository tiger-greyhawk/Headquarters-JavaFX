package name.timoshenko.communityhelper.server.model.domain;

/**
 *
 */
public class Player {

    private final Long id;
    private final Long userId;
    private final String nick;

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
