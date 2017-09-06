package name.timoshenko.communityhelper.server.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "world")
public class WorldAdminAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public WorldAdminAccess() { this(0L, "");
    }

    public WorldAdminAccess(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "WorldAdminAccess{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
