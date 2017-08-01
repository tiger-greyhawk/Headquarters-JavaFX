package name.timoshenko.communityhelper.server.model.domain.acl;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "acl_entry")
public class AclEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(name = "acl_object_identity", nullable = false)
    private Long acl_object_identity;

    @Column(name = "ace_order", nullable = false)
    private int ace_order;

    @Column(name = "sid", nullable = false)
    private Long sid;

    @Column(name = "mask", nullable = false)
    private int mask;

    @Column(name = "granting", nullable = false)
    private boolean granting;

    @Column(name = "audit_success", nullable = false)
    private boolean audit_success;

    @Column(name = "audit_failure", nullable = false)
    private boolean audit_failure;

    public AclEntry() {
        this(0L, 0L, 0, 0L, 0, false, false, false);
    }

    public AclEntry(Long id, Long acl_object_identity, int ace_order, Long sid, int mask, boolean granting, boolean audit_success, boolean audit_failure) {
        this.id = id;
        this.acl_object_identity = acl_object_identity;
        this.ace_order = ace_order;
        this.sid = sid;
        this.mask = mask;
        this.granting = granting;
        this.audit_success = audit_success;
        this.audit_failure = audit_failure;
    }

    public Long getId() {
        return id;
    }

    public Long getAcl_object_identity() {
        return acl_object_identity;
    }

    public int getAce_order() {
        return ace_order;
    }

    public Long getSid() {
        return sid;
    }

    public int getMask() {
        return mask;
    }

    public boolean isGranting() {
        return granting;
    }

    public boolean isAudit_success() {
        return audit_success;
    }

    public boolean isAudit_failure() {
        return audit_failure;
    }
}
