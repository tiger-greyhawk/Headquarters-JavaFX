package name.timoshenko.communityhelper.server.model.domain;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "factions")
public class Faction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Column(name = "name", unique = true, nullable = false)
    private final String name;
    @Column(name = "slogan")
    private final String slogan;
    @Column(name = "owner_id", nullable = false)
    private final Long ownerId;

    public Faction() {
        this(null, null, "", null);
    }

    public Faction(Long id, String name, String slogan, Long ownerId) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.slogan = slogan;
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

    @Override
    public String toString() {
        return "Faction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slogan='" + slogan + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }
}
