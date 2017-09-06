package name.timoshenko.communityhelper.common.model;

import com.canoo.dolphin.collections.ObservableList;
import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

import java.io.Serializable;

@DolphinBean
public class MyPlayersListWindowModel implements Serializable {

    private Property<Boolean> windowVisible;

    private Property<String> myActivePlayerNickname;

    private ObservableList<PlayerModel> myPlayers;

    private ObservableList<WorldAdminModel> worlds;

    private Property<PlayerModel> selectedPlayer;

    private Property<PlayerModel> newPlayer;

    public Property<Boolean> windowVisibleProperty() {
        return windowVisible;
    }

    public Property<String> myActivePlayerNicknameProperty() {
        return myActivePlayerNickname;
    }

    public ObservableList<PlayerModel> myPlayersProperty() {
        return myPlayers;
    }

    public ObservableList<WorldAdminModel> worldsProperty() {
        return worlds;
    }

    public Property<PlayerModel> selectedPlayerProperty() {
        return selectedPlayer;
    }

    public Property<PlayerModel> newPlayerProperty() {
        return newPlayer;
    }
}
