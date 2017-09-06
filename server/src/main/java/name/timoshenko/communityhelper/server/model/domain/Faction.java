package name.timoshenko.communityhelper.server.model.domain;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "factions",
uniqueConstraints = {
@UniqueConstraint(columnNames = {"name", "world_id"})
})
public class Faction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Column(name = "name", nullable = false)
    private final String name;
    @Column(name = "slogan")
    private final String slogan;
    @Column(name = "owner_id", nullable = false)
    private final Long ownerId;
    @Column(name = "world_id", nullable = false)
    private final Long worldId;

    public Faction() {
        this(null, null, "", null, null);
    }

    public Faction(Long id, String name, String slogan, Long ownerId, Long worldId) {
        this.id = id;
        this.name = name;
        this.slogan = slogan;
        this.ownerId = ownerId;
        this.worldId = worldId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlogan() {
        return slogan;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Long getWorldId() {
        return worldId;
    }

    @Override
    public String toString() {
        return "Faction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slogan='" + slogan + '\'' +
                ", ownerId=" + ownerId +
                ", worldId=" + worldId +
                '}';
    }
}
