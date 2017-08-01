package name.timoshenko.communityhelper.server.model.domain.acl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface AclClassRepository extends JpaRepository<AclClass, Long> {

    AclClass findByobjectIdentityClass(String objectIdentityClass);
}
