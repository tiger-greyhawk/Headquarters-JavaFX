package name.timoshenko.communityhelper.common.model;

import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

/**
 *
 */
@DolphinBean
public class LoginWindowModel {
    private Property<CurrentUserModel> currentUserModel;

    private Property<String> message;

    public Property<CurrentUserModel> currentUserModelProperty() {
        return currentUserModel;
    }
    public Property<String> messageProperty() {
        return message;
    }
}
