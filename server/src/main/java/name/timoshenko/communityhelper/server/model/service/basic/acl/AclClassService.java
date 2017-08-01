package name.timoshenko.communityhelper.server.model.service.basic.acl;

import name.timoshenko.communityhelper.server.model.domain.acl.AclClass;
import name.timoshenko.communityhelper.server.model.domain.acl.AclClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class AclClassService {

    @Autowired
    AclClassRepository aclClassRepository;

    public List<AclClass> getAll(){
        return aclClassRepository.findAll();
    }

    public AclClass findOne(Long id){
        return aclClassRepository.findOne(id);
    }

    public AclClass findByobjectIdentityClass(String objectIdentityClass){
        return aclClassRepository.findByobjectIdentityClass(objectIdentityClass);
    }
}
