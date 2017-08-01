package name.timoshenko.communityhelper.server.model.domain.acl.myAcl;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 */
@Entity
@Table(name = "permission")
public class Permission implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(name = "group_name", unique = true, nullable = false)
    private String groupName;

    /*** unix-like permission: xxxxxxx = self, faction, ally(groups of faction), reserved, reserved, reserved, other
     * Скорее всего раскидаю на разные поля, но в этом случае будет сложнее увеличивать/изменять группы доступа?
     */
    @Column(name = "group_permission_mask", nullable = false)
    private int groupPermissionMask;

    /***
     * Класс для которого устанавливаются права. Нужно для поиска id в базе именно этого класса.
     */
    @Column(name = "class_to_permission", nullable = false)
    private String classToPermission;

    @Column(name = "owner_player_id", nullable = false)
    private String ownerPlayerId;

    @Column(name = "owner_user_id", nullable = false)
    private String ownerUserId;

    public Permission(Long id, String groupName, int groupPermissionMask, String classToPermission, String ownerPlayerId, String ownerUserId) {
        this.id = id;
        this.groupName = groupName;
        this.groupPermissionMask = groupPermissionMask;
        this.classToPermission = classToPermission;
        this.ownerPlayerId = ownerPlayerId;
        this.ownerUserId = ownerUserId;
    }
}
