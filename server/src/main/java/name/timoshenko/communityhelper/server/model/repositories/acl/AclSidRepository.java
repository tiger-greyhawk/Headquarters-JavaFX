package name.timoshenko.communityhelper.server.model.repositories.acl;

import name.timoshenko.communityhelper.server.model.domain.acl.AclSid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 *
 */
@Repository
public interface AclSidRepository extends JpaRepository<AclSid, Long> {
}
