package name.timoshenko.communityhelper.server.controller.facade.basic;

import name.timoshenko.communityhelper.server.controller.Security.SecurityContextHolderService;
import name.timoshenko.communityhelper.server.controller.facade.FactionListFacade;
import name.timoshenko.communityhelper.server.model.domain.FactionAlly;
import name.timoshenko.communityhelper.server.model.domain.Faction;
import name.timoshenko.communityhelper.server.model.domain.Player;
import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.service.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasicFactionListFacade implements FactionListFacade {

    private org.slf4j.Logger LOG = LoggerFactory.getLogger(FactionListFacade.class);

    private final FactionService factionService;
    private final FactionPlayerService factionPlayerService;
    private final SecurityContextHolderService contextHolderService;
    private final UserActivePlayerService userActivePlayerService;
    private final FactionAllyService factionAllyService;

    public BasicFactionListFacade(@Qualifier("cachedFactionService") FactionService factionService,
                                  //@Qualifier("basicFactionService") FactionService factionService,
                                  FactionPlayerService factionPlayerService,
                                  SecurityContextHolderService securityContextHolderService,
                                  UserActivePlayerService userActivePlayerService,
                                  FactionAllyService factionAllyService) {
        this.factionService = factionService;
        this.factionPlayerService = factionPlayerService;
        this.contextHolderService = securityContextHolderService;
        this.userActivePlayerService = userActivePlayerService;
        this.factionAllyService = factionAllyService;
    }

    @Override
    public List<Faction> getFactions(final String filter){
        return factionService.getFactions(filter);
    }

    @Override
    public Faction createFaction(String factionName, String factionSlogan, Long worldId){
        Player activePlayer = userActivePlayerService.getActivePlayer(contextHolderService.getCurrentUser().getId());
        Faction factionToCreate = new Faction(-1L, factionName, factionSlogan, activePlayer.getId(), worldId);
        Faction createdFaction = factionService.createFaction(factionToCreate);
        factionPlayerService.addPlayerToFaction(activePlayer, createdFaction, true, true);
        return createdFaction;
    }

    @Override
    public boolean isCurrentUserOwnsCurrentFaction(Long factionOwnerId) {
        //final Long factionOwnerId = model.selectedFactionProperty().get().getOwnerId();
        Player activePlayer = userActivePlayerService.getActivePlayer(contextHolderService.getCurrentUser().getId());
        return factionOwnerId.equals(activePlayer.getId());
    }

    /*TODO времянка проверки союзного статуса. Чисто для заполнения модели отображения.
     */
    @Override
    public Optional<String> getAlliedStatus(Faction factionToCheck){
        Optional<String> result = Optional.empty();
        User currentUser = new User();
        try {
            currentUser = contextHolderService.getCurrentUser();
        } catch (NotFoundException nfe){
            LOG.info("Такого пользователя нет в БД", nfe.getMessage());
            return result;
        }
        if ((currentUser == null) || (currentUser.getId().equals(-1L)))return Optional.empty();
        Optional<Faction> myFaction = getMyCurrentFaction();
        if (!myFaction.isPresent()) return Optional.empty();
        /*TODO переделать List<FactionAlly>
        Здесь слишком дорого объявлять этот массив.
         */
        final List<FactionAlly> allyFactions = factionAllyService.findFactionAllies(myFaction.get().getId());
        for (FactionAlly ally:allyFactions){
            //if ((ally.getFirstFactionId().equals(myFaction.get().getId()) &&  ally.getSecondFactionId().equals(factionToCheck.getId()))
            //        || (ally.getFirstFactionId().equals(factionToCheck.getId()) && ally.getSecondFactionId().equals(myFaction.get().getId())))
                //result = ally.getAllyType().name();
            int resultCheck = factionAllyService.checkBothRecordAlly(myFaction.get().getId(), factionToCheck.getId());
            if (resultCheck == 2)
                result = Optional.of(ally.getAllyType());
            if (resultCheck == 1)
                result = Optional.of(ally.getAllyType()+"(outgoing)");
            if (resultCheck == 0)
                result = Optional.of(ally.getAllyType()+"(incoming)");
            if (resultCheck == -1)
                result = Optional.of("");
        }
        return result;
    }

    //@Override
/*    private int checkBothRecordAlly(List<FactionAlly> allyFactions, Optional<Faction> myFaction, Faction factionToCheck){

        if (allyFactions.containsAll())
    }*/

    @Override
    public Optional<Faction> getMyCurrentFaction(){
        Optional<Long> factionId = factionPlayerService.findFactionByPlayerId(
                userActivePlayerService.getActivePlayer(
                        contextHolderService.getCurrentUser().getId()
                ).getId());
        if (!factionId.isPresent())
            return Optional.empty();
        return factionService.getFactionById(factionId.get());
    }
}
