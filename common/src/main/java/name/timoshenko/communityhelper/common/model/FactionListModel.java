package name.timoshenko.communityhelper.common.model;

import com.canoo.dolphin.collections.ObservableList;
import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

import java.io.Serializable;

/**
 *
 */
@DolphinBean
public class FactionListModel implements Serializable {

    private Property<CurrentUserModel> currentUser;

    private Property<String> filter;

    private ObservableList<FactionModel> factions;

    private ObservableList<PlayerModel> players;

    private Property<FactionModel> selectedFaction;

    private Property<PlayerModel> selectedPlayer;

    private Property<Boolean> canDeleteCurrentFaction;

    public Property<CurrentUserModel> currentUserModelProperty() {
        return currentUser;
    }

    public Property<String> filterProperty() {
        return filter;
    }

    public ObservableList<FactionModel> factionsProperty() {
        return factions;
    }

    public Property<FactionModel> selectedFactionProperty() {
        return selectedFaction;
    }

    public Property<PlayerModel> selectedPlayerProperty() {
        return selectedPlayer;
    }

    public ObservableList<PlayerModel> playersProperty() {
        return players;
    }

    public Property<Boolean> cannotDeleteCurrentFactionProperty() {
        return canDeleteCurrentFaction;
    }
}
