package name.timoshenko.communityhelper.server.controller;

import com.canoo.platform.server.event.Topic;
import name.timoshenko.communityhelper.common.model.CurrentUserModel;
import name.timoshenko.communityhelper.common.model.FactionListModel;

/**
 *
 */
public interface EventTopics {
    Topic<CurrentUserModel> LOGIN_TOPIC = new Topic<>();
    Topic<FactionListModel> FACTION_LIST_SHOW_TOPIC = new Topic("factionListShow");
}
