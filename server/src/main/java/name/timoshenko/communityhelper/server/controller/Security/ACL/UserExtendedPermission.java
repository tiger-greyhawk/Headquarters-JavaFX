package name.timoshenko.communityhelper.server.controller.Security.ACL;

import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.model.Permission;

/**
 * http://grzegorzborkowski.blogspot.ru/2008/10/spring-security-acl-very-basic-tutorial.html
 */
public class UserExtendedPermission extends BasePermission {
    private static final long serialVersionUID = 1L;

    public static final Permission ACCEPT = new UserExtendedPermission(1 << 5, 'a'); // 32

    /**
     * The char code is used only for textual-representation of the permission, and has no influence on any logic.
     * BasePermission uses capital letter 'A' for ADMINISTRATION, so I used 'a' for ACCEPT. As I said, it really doesn't matter.
     * We used sixth bit (1 << 5) in the internal Permission implementation (first five bits are used by built-in types).
     * I will not dig into internals of Permissions concept, as it is well described in Spring security documentation and not very difficult.
     * Now we turn our attation to Acl implementation:
     * Взято отсюда: http://grzegorzborkowski.blogspot.ru/2008/10/spring-security-acl-very-basic-tutorial.html
     */

    /**
     * Registers the public static permissions defined on this class. This is
     * mandatory so that the static methods will operate correctly. (copied from
     * super class)
     */
    /*static {
        registerPermissionsFor(UserExtendedPermission.class);
    }*/

    private UserExtendedPermission(int mask, char code) {
        super(mask, code);
    }
}
