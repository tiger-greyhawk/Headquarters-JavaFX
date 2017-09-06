package name.timoshenko.communityhelper.server.model.service.basic;

import name.timoshenko.communityhelper.server.model.domain.RequestResource;
import name.timoshenko.communityhelper.server.model.repositories.RequestResourceRepository;
import name.timoshenko.communityhelper.server.model.service.RequestResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("basicRequestResourceService")
public class BasicRequestResourceService implements RequestResourceService {

    private final RequestResourceRepository requestResourceRepository;

    public BasicRequestResourceService(RequestResourceRepository requestResourceRepository) {
        this.requestResourceRepository = requestResourceRepository;
    }

    @Override
    public List<RequestResource> getRequestsResources() {
        return requestResourceRepository.findAll();
    }

    @Override
    public List<RequestResource> getRequestsResources(Long currentuserId) {
        return requestResourceRepository.findAll();
    }

    @Override
    public List<RequestResource> findRequestResourceByPlayerId(Long playerId) {
        return requestResourceRepository.findRequestResourceByPlayerId(playerId);
    }

    @Override
    public RequestResource addRequestResource(RequestResource requestResource) {
        return requestResourceRepository.save(requestResource);
    }
}
