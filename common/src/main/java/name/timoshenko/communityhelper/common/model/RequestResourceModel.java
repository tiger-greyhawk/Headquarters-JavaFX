package name.timoshenko.communityhelper.common.model;


import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

import java.io.Serializable;

@DolphinBean
public class RequestResourceModel implements Serializable {

    private Property<Long> id;
    private Property<PlayerModel> player;
    private Property<String> name;
    private Property<String> amount;

    public Property<Long> idProperty() {
        return id;
    }

    public Property<PlayerModel> playerProperty() {
        return player;
    }

    public Property<String> nameProperty() {
        return name;
    }

    public Property<String> amountProperty() {
        return amount;
    }

    public Long getId() {
        return id.get();
    }

    public PlayerModel getPlayer() {
        return player.get();
    }

    public String getName() {
        return name.get();
    }

    public String getAmount() {
        return amount.get();
    }
}
