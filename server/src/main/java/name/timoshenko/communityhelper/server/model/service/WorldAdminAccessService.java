package name.timoshenko.communityhelper.server.model.service;

import name.timoshenko.communityhelper.server.model.domain.WorldAdminAccess;

import java.util.List;

public interface WorldAdminAccessService {
    List<WorldAdminAccess> getAll();
    WorldAdminAccess getWorld(Long id);
}
