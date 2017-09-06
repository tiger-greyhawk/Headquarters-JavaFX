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
    private Property<WorldAdminModel> world;
    private Property<Long> userId;
    private Property<Boolean> canNotPlayerCreateFaction;

    public Long getId() {
        return id.get();
    }

    public String getNickName() {
        return nickname.get();
    }

    public WorldAdminModel getWorld() {
        return world.get();
    }

    public Long getUserId() {
        return userId.get();
    }

    public Boolean getCanNotPlayerCreateFaction(){
        return canNotPlayerCreateFaction.get();
    }

    public Property<Long> idProperty() {
        return id;
    }

    public Property<String> nicknameProperty() {
        return nickname;
    }

    public Property<WorldAdminModel> worldProperty() {
        return world;
    }

    public Property<Long> userIdProperty() {
        return userId;
    }

    public Property<Boolean> canNotPlayerCreateFactionProperty() {
        return canNotPlayerCreateFaction;
    }
}
