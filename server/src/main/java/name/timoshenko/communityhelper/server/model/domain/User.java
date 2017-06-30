package name.timoshenko.communityhelper.server.model.domain;

/**
 *
 */
public class User {

    private final Long id;
    private final String login;
    private final String passwordHash;

    public User() {
        this(null, null, null);
    }

    public User(Long id, String login, String passwordHash) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }
}
