package name.timoshenko.communityhelper.server.controller.Security.ACL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import name.timoshenko.communityhelper.server.model.domain.User;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.security.acls.domain.AccessControlEntryImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * http://grzegorzborkowski.blogspot.ru/2008/10/spring-security-acl-very-basic-tutorial.html
 */
/**
 * The simplest possible implementation of AclService interface. Uses in-memory
 * collection of ACLs, providing fast and easy access to them.
 *
 */
@Service
public class InMemoryAclServiceImpl implements AclService {

    Map<ObjectIdentity, Acl> acls = new HashMap<ObjectIdentity, Acl>();

    @PostConstruct
    public void initializeACLs() {
        // create ACLs according to requirements of tutorial application
        ObjectIdentity user1 = new ObjectIdentityImpl(User.class, "user1");
        ObjectIdentity user2 = new ObjectIdentityImpl(User.class, "user2");
        ObjectIdentity user3 = new ObjectIdentityImpl(User.class, "user3");
        ObjectIdentity user4 = new ObjectIdentityImpl(User.class, "user4");



        Acl acl1 = new SimpleAclImpl(user1, new ArrayList<AccessControlEntry>(1));
        acl1.getEntries().add( new AccessControlEntryImpl("ace1", acl1, new PrincipalSid("admin1"), UserExtendedPermission.ACCEPT, true, true, true));
        acls.put(acl1.getObjectIdentity(), acl1);
        Acl acl2 = new SimpleAclImpl(user2, new ArrayList<AccessControlEntry>(1));
        acl2.getEntries().add(new AccessControlEntryImpl("ace2", acl2, new PrincipalSid("admin1"), UserExtendedPermission.ACCEPT, true, true, true));
        acls.put(acl2.getObjectIdentity(), acl2);
        Acl acl3 = new SimpleAclImpl(user3, new ArrayList<AccessControlEntry>(1));
        acl3.getEntries().add( new AccessControlEntryImpl("ace3", acl3, new PrincipalSid("admin2"), UserExtendedPermission.ACCEPT, true, true, true));
        acls.put(acl3.getObjectIdentity(), acl3);
        Acl acl4 = new SimpleAclImpl(user4, new ArrayList<AccessControlEntry>(1));
        acl4.getEntries().add( new AccessControlEntryImpl("ace4", acl4, new PrincipalSid("admin2"), UserExtendedPermission.ACCEPT, true, true, true));
        acls.put(acl4.getObjectIdentity(), acl4);
        //throw new UnsupportedOperationException("Надо попробовать реализовать не в мемори, а из репозитори. Как остальные энтити.");   // Надо попробовать реализовать не в мемори, а из репозитори. Как остальные энтити.
    }

    public List<ObjectIdentity> findChildren(ObjectIdentity parentIdentity) {
        // I'm not really sure what this method should do...
        throw new UnsupportedOperationException("Not implemented");
    }


    @Override
    public Acl readAclById(ObjectIdentity object) throws NotFoundException {
        return readAclById(object, null);
    }

    @Override
    public Acl readAclById(ObjectIdentity objectIdentity, List<Sid> sids) throws NotFoundException {
        Map<ObjectIdentity, Acl> map = readAclsById(new ArrayList<ObjectIdentity>() , sids);
        Assert.isTrue(map.containsKey(objectIdentity), "There should have been an Acl entry for ObjectIdentity " + objectIdentity);

        return map.get(objectIdentity);
    }

    @Override
    public Map<ObjectIdentity, Acl> readAclsById(List<ObjectIdentity> list) throws NotFoundException {
        return readAclsById(list, null);
    }

    @Override
    public Map<ObjectIdentity, Acl> readAclsById(List<ObjectIdentity> list, List<Sid> sids) throws NotFoundException {
        Map<ObjectIdentity, Acl> result = new HashMap<ObjectIdentity, Acl>();

        for (ObjectIdentity object : list) {
            if (acls.containsKey(object)) {
                result.put(object, acls.get(object));
            } else {
                throw new NotFoundException("Unable to find ACL information for object identity '" + object.toString() + "'");
            }
        }

        return result;
    }

}