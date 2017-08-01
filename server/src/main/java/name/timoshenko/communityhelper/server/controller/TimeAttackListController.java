package name.timoshenko.communityhelper.server.controller;

import com.canoo.platform.server.DolphinController;
import com.canoo.platform.server.DolphinModel;
import name.timoshenko.communityhelper.common.Constants;
import name.timoshenko.communityhelper.common.model.TimeAttackListWindowModel;

/**
 *
 */
@DolphinController(Constants.TIME_ATTACK_LIST_CONTROLLER_NAME)
public class TimeAttackListController {

    @DolphinModel
    TimeAttackListWindowModel timeAttackListWindowModel;
}
