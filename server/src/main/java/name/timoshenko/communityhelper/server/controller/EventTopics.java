package name.timoshenko.communityhelper.server.controller;

import com.canoo.platform.server.event.Topic;
import name.timoshenko.communityhelper.common.model.CurrentUserModel;
import name.timoshenko.communityhelper.common.model.FactionListWindowModel;

/**
 *
 */
public interface EventTopics {
    Topic<CurrentUserModel> LOGIN_TOPIC = new Topic<>();
    Topic<FactionListWindowModel> FACTION_LIST_SHOW_TOPIC = new Topic("factionListShow");
}
