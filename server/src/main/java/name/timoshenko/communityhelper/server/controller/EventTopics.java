package name.timoshenko.communityhelper.server.controller;

import com.canoo.platform.server.event.Topic;
import name.timoshenko.communityhelper.common.model.CurrentUserModel;

/**
 *
 */
public interface EventTopics {
    Topic<CurrentUserModel> LOGIN_TOPIC = new Topic<>();
}
