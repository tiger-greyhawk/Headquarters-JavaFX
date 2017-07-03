package name.timoshenko.communityhelper.common.model;

import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

import java.io.Serializable;

/**
 * Created by Tiger on 03.07.2017.
 */
@DolphinBean
public class MainModel implements Serializable{

    private Property<CurrentUserModel> currentUserModel;
    public Property<CurrentUserModel> currentUserModelProperty() {
        return currentUserModel;
    }
}
