package name.timoshenko.communityhelper.common.model;

import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

/**
 *
 */
@DolphinBean
public class FactionModel {
    private Property<Long> id;
    private Property<String> name;
    private Property<String> world;
    private Property<String> slogan;
    private Property<Long> ownerId;
    private Property<String> ownerName;
    private Property<String> alliedFaction;
    private Property<String> typeAlly;

    public Property<Long> idProperty() {
        return id;
    }

    public Property<String> nameProperty() {
        return name;
    }

    public Property<String> worldProperty() {
        return world;
    }

    public Property<String> sloganProperty() {
        return slogan;
    }

    public Property<Long> ownerIdProperty() {
        return ownerId;
    }

    public Property<String> ownerNameProperty() {
        return ownerName;
    }

    public Property<String> alliedFactionProperty() {
        return alliedFaction;
    }

    public Property<String> typeAllyProperty() {
        return typeAlly;
    }

    public Long getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getWorld() {
        return world.get();
    }

    public String getSlogan() {
        return slogan.get();
    }

    public Long getOwnerId() {
        return ownerId.get();
    }

    public String getOwnerName() {
        return ownerName.get();
    }

    public String getAlliedFaction() {return alliedFaction.get(); }

    public String getTypeAlly() {return typeAlly.get(); }
}
