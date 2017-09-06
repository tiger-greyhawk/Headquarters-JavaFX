package name.timoshenko.communityhelper.client.controller.event;

import com.canoo.platform.client.Param;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import name.timoshenko.communityhelper.client.controller.dialog.FactionListDialogService;
import name.timoshenko.communityhelper.client.controller.dialog.SimpleFactionListDialogService;
import name.timoshenko.communityhelper.common.AllyTypeConstants;

public class BasicFactionListEvent implements FactionListEvent {

    private FactionListDialogService factionListDialogService;

    public BasicFactionListEvent(){
        factionListDialogService = new SimpleFactionListDialogService();
    }

    @Override
    public EventHandler<ActionEvent> setAllyFaction(String factionName){
        String allyNote = factionListDialogService.ShowAllyFactionDialog(factionName);
        //if (allyNote.isEmpty()) return;
        Param allyNoteParam = new Param("note", allyNote);
        /*TODO так нельзя, надо наследоваться от существующего энума в entity AllyOfFaction. Но как? Оно ведь в package server!
         */
        Param typeAlly = new Param("type", AllyTypeConstants.NULLY);
        //invoke(Constants.SET_ALLY_FACTION_EVENT, allyNoteParam);
        return this;
    }

    @Override
    public void handle(ActionEvent event) {

    }
}
