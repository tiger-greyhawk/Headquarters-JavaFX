package name.timoshenko.communityhelper.server.controller.service.basic;

import name.timoshenko.communityhelper.server.controller.Security.SecurityContextHolderService;
import name.timoshenko.communityhelper.server.controller.service.FactionListService;
import name.timoshenko.communityhelper.server.model.domain.AllyOfFaction;
import name.timoshenko.communityhelper.server.model.domain.Faction;
import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.service.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicFactionListService implements FactionListService {

    private org.slf4j.Logger LOG = LoggerFactory.getLogger(FactionListService.class);

    private final FactionService factionService;
    private final FactionPlayerService factionPlayerService;
    private final SecurityContextHolderService contextHolderService;
    private final UserActivePlayerService userActivePlayerService;
    private final AllyOfFactionService allyOfFactionService;

    public BasicFactionListService(@Qualifier("cachedFactionService") FactionService factionService,
                                   FactionPlayerService factionPlayerService,
                                   SecurityContextHolderService securityContextHolderService,
                                   UserActivePlayerService userActivePlayerService,
                                   AllyOfFactionService allyOfFactionService) {
        this.factionService = factionService;
        this.factionPlayerService = factionPlayerService;
        this.contextHolderService = securityContextHolderService;
        this.userActivePlayerService = userActivePlayerService;
        this.allyOfFactionService = allyOfFactionService;
    }

    @Override
    public List<Faction> getFactions(final String filter){
        return factionService.getFactions(filter);
    }

    @Override
    public boolean isCurrentUserOwnsCurrentFaction(Long factionOwnerId) {
        //final Long factionOwnerId = model.selectedFactionProperty().get().getOwnerId();
        Player activePlayer = userActivePlayerService.getActivePlayer(contextHolderService.getCurrentUser().getId());
        return factionOwnerId.equals(activePlayer.getId());
    }

    @Override
    public String getAlliedStatus(Faction factionToCheck){
        String result = "";
        User currentUser = new User();
        try {
            currentUser = contextHolderService.getCurrentUser();
        } catch (NotFoundException nfe){
            LOG.info("Такого пользователя нет в БД", nfe.getMessage());
            return result;
        }
        if ((currentUser == null) || (currentUser.getId().equals(-1L)))return null;
        Faction myFaction = getMyCurrentFaction();
        /*TODO переделать List<AllyOfFaction>
        Здесь слишком дорого объявлять этот массив.
         */
        final List<AllyOfFaction> allyFactions = allyOfFactionService.findAllyFactions(myFaction.getId());
        for (AllyOfFaction ally:allyFactions){
            if ((ally.getFirstFactionId().equals(myFaction.getId()) &&  ally.getSecondFactionId().equals(factionToCheck.getId()))
                    || (ally.getFirstFactionId().equals(factionToCheck.getId()) && ally.getSecondFactionId().equals(myFaction.getId())))
                //result = ally.getAllyType().name();
                result = ally.getNote();
        }
        return result;

    }

    @Override
    public Faction getMyCurrentFaction(){
        return factionService.getFactionById(
                factionPlayerService.findFactionByPlayerId(
                        userActivePlayerService.getActivePlayer(
                                contextHolderService.getCurrentUser().getId()
                        ).getId())).orElse(new Faction(-1L, "without faction", -1L));
    }
}
