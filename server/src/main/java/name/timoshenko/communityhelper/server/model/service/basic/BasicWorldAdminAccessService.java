package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.domain.WorldAdminAccess;
import name.timoshenko.communityhelper.server.model.repositories.WorldAdminAccessRepository;
import name.timoshenko.communityhelper.server.model.service.WorldAdminAccessService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BasicWorldAdminAccessService implements WorldAdminAccessService {

    private final WorldAdminAccessRepository worldAdminAccessRepository;

    public BasicWorldAdminAccessService(WorldAdminAccessRepository worldAdminAccessRepository) {
        this.worldAdminAccessRepository = worldAdminAccessRepository;
    }

    @Override
    public List<WorldAdminAccess> getAll() {
        return worldAdminAccessRepository.findAll();
    }

    @Override
    public WorldAdminAccess getWorld(Long id){
        return worldAdminAccessRepository.findOne(id);
    }
}
