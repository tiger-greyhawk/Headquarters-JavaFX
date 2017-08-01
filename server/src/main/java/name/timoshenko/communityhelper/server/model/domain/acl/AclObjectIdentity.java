package name.timoshenko.communityhelper.server.model.domain.acl;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "acl_object_identity")
public class AclObjectIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(name = "object_id_class", nullable = false)
    private Long object_id_class;

    @Column(name = "object_id_identity", nullable = false)
    private Long object_id_identity;

    @Column(name = "parent_object")
    private Long parent_object;

    @Column(name = "owner_sid")
    private Long owner_sid;

    @Column(name = "entries_inheriting", nullable = false)
    private boolean entries_inheriting;

    public AclObjectIdentity() {
        this(0L, 0L, 0L, null, 0L, false);
    }

    public AclObjectIdentity(Long id, Long object_id_class, Long object_id_identity, Long parent_object, Long owner_sid, boolean entries_inheriting) {
        this.id = id;
        this.object_id_class = object_id_class;
        this.object_id_identity = object_id_identity;
        this.parent_object = parent_object;
        this.owner_sid = owner_sid;
        this.entries_inheriting = entries_inheriting;
    }

    public Long getId() {
        return id;
    }

    public Long getObject_id_class() {
        return object_id_class;
    }

    public Long getObject_id_identity() {
        return object_id_identity;
    }

    public Long getParent_object() {
        return parent_object;
    }

    public Long getOwner_sid() {
        return owner_sid;
    }

    public boolean isEntries_inheriting() {
        return entries_inheriting;
    }
}
