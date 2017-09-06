package name.timoshenko.communityhelper.common.model;

import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

import java.io.Serializable;

/**
 * Main window model is responsible for entire main window properties.
 */
@DolphinBean
public class MainWindowModel implements Serializable{

    private Property<Boolean> factionWindowVisible;

    private Property<Boolean> requestResourceWindowVisible;

    private Property<Boolean> myPlayersListWindowVisible;

    private Property<CurrentUserModel> currentUserModel;

    public Property<CurrentUserModel> currentUserModelProperty() {
        return currentUserModel;
    }

    public Property<Boolean> factionWindowVisibleProperty() {
        return factionWindowVisible;
    }

    public Property<Boolean> requestResourceWindowVisibleProperty() {
        return requestResourceWindowVisible;
    }

    public Property<Boolean> myPlayersListWindowVisibleProperty() {
        return myPlayersListWindowVisible;
    }
}
