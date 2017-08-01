package name.timoshenko.communityhelper.server.model.domain.acl;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 */
@Entity
@Table(name = "acl_class")
public class AclClass implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    //principal если true, то sid указывает на владельца, если false, то sid указывает на роль.
    @Column(name = "class", unique = true, nullable = false)
    private final String objectIdentityClass;

    public AclClass() { this(0L, "");
    }

    public AclClass(Long id, String objectIdentityClass) {
        this.id = id;
        this.objectIdentityClass = objectIdentityClass;
    }

    public Long getId() {
        return id;
    }

    public String getObjectIdentityClass() {
        return objectIdentityClass;
    }
}
