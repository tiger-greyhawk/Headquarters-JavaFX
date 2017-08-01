package name.timoshenko.communityhelper.server.model.domain.acl;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "acl_sid", uniqueConstraints={
        @UniqueConstraint(columnNames={"principal", "sid"})
})
public class AclSid {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    //principal если true, то sid указывает на владельца, если false, то sid указывает на роль.
    @Column(name = "principal", nullable = false)
    private boolean principal;

    @Column(name = "sid", nullable = false)
    private String sid;

    public AclSid() {
        this(0L, true, "");
    }

    public AclSid(Long id, boolean principal, String sid) {
        this.id = id;
        this.principal = principal;
        this.sid = sid;
    }

    public Long getId() {
        return id;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public String getSid() {
        return sid;
    }
}
