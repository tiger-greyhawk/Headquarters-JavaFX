package name.timoshenko.communityhelper.common.model;

import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

/**
 *
 */
@DolphinBean
public class PlayerModel {

    private Property<Long> id;
    private Property<String> nickname;
    private Property<Long> userId;

    public Long getId() {
        return id.get();
    }

    public String getNickName() {
        return nickname.get();
    }

    public Long getUserId() {
        return userId.get();
    }

    public Property<Long> idProperty() {
        return id;
    }

    public Property<String> nicknameProperty() {
        return nickname;
    }

    public Property<Long> userIdProperty() {
        return userId;
    }
}
