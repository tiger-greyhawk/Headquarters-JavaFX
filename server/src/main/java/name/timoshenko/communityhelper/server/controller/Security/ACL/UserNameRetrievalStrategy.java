package name.timoshenko.communityhelper.server.controller.Security.ACL;

import name.timoshenko.communityhelper.server.model.domain.User;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.ObjectIdentityRetrievalStrategy;


/**
 * http://grzegorzborkowski.blogspot.ru/2008/10/spring-security-acl-very-basic-tutorial.html
 */



/** overwrite the strategy: build ObjectIdentity based on user object login property,
 *  instead of Spring Security default getId() call
 */
public class UserNameRetrievalStrategy implements ObjectIdentityRetrievalStrategy {

    public ObjectIdentity getObjectIdentity(Object domainObject) {
        User user = (User) domainObject;
        return new ObjectIdentityImpl(User.class, user.getLogin());
    }

}