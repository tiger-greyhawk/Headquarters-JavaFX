package name.timoshenko.communityhelper.server.model.domain;

/**
 *
 */
public class Faction {

    private final Long id;
    private final String name;
    private final Long ownerId;

    public Faction() {
        this(null, null, null);
    }

    public Faction(Long id, String name, Long ownerId) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    @Override
    public String toString() {
        return "Faction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }
}
