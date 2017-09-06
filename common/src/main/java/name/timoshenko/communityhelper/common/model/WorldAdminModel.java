package name.timoshenko.communityhelper.common.model;

import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

@DolphinBean
public class WorldAdminModel {
    private Property<Long> id;
    private Property<String> name;

    public Property<Long> idProperty() {
        return id;
    }

    public Property<String> nameProperty() {
        return name;
    }

    public Long getId() {
        return id.get();
    }

    public String getName(){
        return name.get();
    }
}
