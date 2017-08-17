package name.timoshenko.communityhelper.client.controller.event;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public interface FactionListEvent extends EventHandler<ActionEvent> {
    EventHandler<ActionEvent> setAllyFaction(String factionName);
}
