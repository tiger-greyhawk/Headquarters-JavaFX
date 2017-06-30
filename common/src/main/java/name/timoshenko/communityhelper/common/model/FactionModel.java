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
    private Property<Long> ownerId;
    private Property<String> ownerName;

    public Property<Long> idProperty() {
        return id;
    }

    public Property<String> nameProperty() {
        return name;
    }

    public Property<Long> ownerIdProperty() {
        return ownerId;
    }

    public Property<String> ownerNameProperty() {
        return ownerName;
    }

    public Long getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public Long getOwnerId() {
        return ownerId.get();
    }

    public String getOwnerName() {
        return ownerName.get();
    }
}
