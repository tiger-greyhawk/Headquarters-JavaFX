package name.timoshenko.communityhelper.server.controller.Security.ACL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.stereotype.Component;

/**
 *
 */
//@Component
public class ExpressionHandler extends org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler {

    @Autowired
    AclPermissionEvaluator aclPermissionEvaluator;
    @Autowired
    RoleHierarchy roleHierarchy;

}
