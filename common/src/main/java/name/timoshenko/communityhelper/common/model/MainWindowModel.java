package name.timoshenko.communityhelper.common.model;

import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

import java.io.Serializable;

/**
 * Main window model is responsible for entire main window properties.
 */
@DolphinBean
public class MainWindowModel implements Serializable{

    private Property<Boolean> factionWinowVisible;

    private Property<CurrentUserModel> currentUserModel;

    public Property<CurrentUserModel> currentUserModelProperty() {
        return currentUserModel;
    }

    public Property<Boolean> factionWinowVisibleProperty() {
        return factionWinowVisible;
    }
}
