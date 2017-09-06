package name.timoshenko.communityhelper.server.controller;

import com.canoo.platform.server.binding.Qualifier;
import name.timoshenko.communityhelper.common.model.CurrentUserModel;

/**
 *
 */
public interface Qualifiers {
    Qualifier<Boolean> FACTION_WINDOW_VISIBLE_QUALIFIER = Qualifier.create();
    Qualifier<Boolean> REQUEST_RESOURCE_WINDOW_VISIBLE_QUALIFIER = Qualifier.create();
    Qualifier<CurrentUserModel> CURRENT_USER_MODEL_QUALIFIER = Qualifier.create();
    Qualifier<Boolean> MY_PLAYERS_LIST_WINDOW_VISIBLE_QUALIFIER = Qualifier.create();
}
