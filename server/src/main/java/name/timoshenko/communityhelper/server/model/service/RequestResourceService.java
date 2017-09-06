package name.timoshenko.communityhelper.server.model.service;

import name.timoshenko.communityhelper.server.model.domain.RequestResource;

import java.util.List;

public interface RequestResourceService {
    List<RequestResource> getRequestsResources();
    List<RequestResource> getRequestsResources(Long currentuserId);
    List<RequestResource> findRequestResourceByPlayerId(Long playerId);
    RequestResource addRequestResource(RequestResource requestResource);


}
