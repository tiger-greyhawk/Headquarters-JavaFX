package name.timoshenko.communityhelper.server.model.service;

import name.timoshenko.communityhelper.server.model.domain.UserRole;

import java.util.List;

/**
 *
 */
public interface UserRoleService {
    List<UserRole> FindUserRolesByUserId(long userId);
}
