package name.timoshenko.communityhelper.common.model;

import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

import java.io.Serializable;

/**
 *
 */
@DolphinBean
public class CurrentUserModel implements Serializable {

    private Property<Long> userId;

    private Property<String> login;

    private Property<String> password;

    private Property<Boolean> loggedIn;

    public Property<String> loginProperty() {
        return login;
    }

    public Property<Long> userIdProperty() {
        return userId;
    }

    public Property<String> passwordProperty() {
        return password;
    }

    public Property<Boolean> loggedInProperty() {
        return loggedIn;
    }
}
