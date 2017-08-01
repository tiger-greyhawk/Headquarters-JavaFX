package name.timoshenko.communityhelper.server.controller.Security;

import name.timoshenko.communityhelper.server.model.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

/**
 *
 */
public interface SecurityContextHolderService {

    SecurityContext getContext();
    Authentication getAuthentication();
    User getCurrentUser();
}
