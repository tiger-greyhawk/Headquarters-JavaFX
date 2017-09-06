package name.timoshenko.communityhelper.server.model.service.ally;

import name.timoshenko.communityhelper.server.controller.Security.SecurityContextHolderService;
import name.timoshenko.communityhelper.server.model.domain.RequestResource;
import name.timoshenko.communityhelper.server.model.service.FactionAllyService;
import name.timoshenko.communityhelper.server.model.service.FactionPlayerService;
import name.timoshenko.communityhelper.server.model.service.RequestResourceService;
import name.timoshenko.communityhelper.server.model.service.UserActivePlayerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("allyRequestResourceService")
public class AllyRequestResourceService implements RequestResourceService{

    private final RequestResourceService requestResourceService;
    private final FactionAllyService factionAllyService;
    private final FactionPlayerService factionPlayerService;
    private final UserActivePlayerService userActivePlayerService;
    private final SecurityContextHolderService contextHolderService;

    public AllyRequestResourceService(@Qualifier("basicRequestResourceService") RequestResourceService requestResourceService,
                                      FactionAllyService factionAllyService,
                                      FactionPlayerService factionPlayerService,
                                      UserActivePlayerService userActivePlayerService,
                                      SecurityContextHolderService contextHolderService) {
        this.requestResourceService = requestResourceService;
        this.factionAllyService = factionAllyService;
        this.factionPlayerService = factionPlayerService;
        this.userActivePlayerService = userActivePlayerService;
        this.contextHolderService = contextHolderService;
    }

    @Override
    public List<RequestResource> getRequestsResources() {
        final List<RequestResource> unfiltered = requestResourceService.getRequestsResources();
        final List<RequestResource> filtered = new ArrayList<>();
        Optional<Long> myFactionId = factionPlayerService.findFactionByPlayerId(userActivePlayerService.getActivePlayer(
                contextHolderService.getCurrentUser().getId()).getId());
        if (!myFactionId.isPresent()) return null;
        for (RequestResource request: unfiltered
             ) {
            Optional<Long> otherFactionId = factionPlayerService.findFactionByPlayerId(request.getPlayerId());
            if (!otherFactionId.isPresent()) return null;
            if (factionAllyService.checkBothRecordAlly(myFactionId.get(), otherFactionId.get()) == 2 || myFactionId.get().equals(otherFactionId.get()))
                filtered.add(request);
        }

        return filtered;
    }

    @Override
    public List<RequestResource> getRequestsResources(Long currentuserId) {
        final List<RequestResource> unfiltered = requestResourceService.getRequestsResources();
        final List<RequestResource> filtered = new ArrayList<>();
        Optional<Long> myFactionId = factionPlayerService.findFactionByPlayerId(userActivePlayerService.getActivePlayer(
                currentuserId).getId());
        if (!myFactionId.isPresent()) return null;
        for (RequestResource request: unfiltered
                ) {
            Optional<Long> otherFactionId = factionPlayerService.findFactionByPlayerId(request.getPlayerId());
            if (!otherFactionId.isPresent()) return null;
            if (factionAllyService.checkBothRecordAlly(myFactionId.get(), otherFactionId.get()) == 2 || myFactionId.get().equals(otherFactionId.get()))
                filtered.add(request);
        }

        return filtered;
    }

    @Override
    public List<RequestResource> findRequestResourceByPlayerId(Long playerId) {
        return requestResourceService.findRequestResourceByPlayerId(playerId);
    }

    @Override
    public RequestResource addRequestResource(RequestResource requestResource) {
        return requestResourceService.addRequestResource(requestResource);
    }
}
