package name.timoshenko.communityhelper.common;

/**
 *
 */
public interface Constants {

    String MAIN_CONTROLLER_NAME = "MAIN_CONTROLLER";
    String TOGGLE_FACTION_WINDOW_ACTION = "TOGGLE_FACTION_WINDOW_ACTION";

    String FACTION_LIST_CONTROLLER_NAME = "FACTION_LIST_CONTROLLER";
    String LOGIN_CONTROLLER_NAME = "LOGIN_CONTROLLER";
    String TIME_ATTACK_LIST_CONTROLLER_NAME = "TIME_ATTACK_LIST_CONTROLLER";
    String REQUEST_RESOURCE_LIST_CONTROLLER_NAME = "REQUEST_RESOURCE_LIST_CONTROLLER";
    String MY_PLAYERS_LIST_CONTROLLER_NAME = "MY_PLAYERS_LIST_CONTROLLER";

    String SHOW_EVENT = "ShowEvent";
    String LOGIN_EVENT = "LoginEvent";
    String LIST_CHANGE_EVENT = "ListChangeEvent";

    /**
     *  Ивенты из окна управлением игроками текущего пользователя.
     */
    String MYPLAYERS_LIST_WINDOW_EVENT_ADD_NEW_PLAYER = "AddNewPlayerEvent";
    String MYPLAYERS_LIST_WINDOW_EVENT_SET_ACTIVE_PLAYER = "SetActivePlayerWindow";

    /**
     *  Ивенты из окна управлением и просмотром фракций.
     */
    String CREATE_FACTION_EVENT = "CreateFactionEvent";
    String DELETE_FACTION_EVENT = "DeleteFactionEvent";
    String SET_ALLY_FACTION_EVENT = "SetAllyFactionEvent";
    String JOIN_FACTION_EVENT = "JoinFactionEvent";
    String LEAVE_FACTION_EVENT = "LeaveFactionEvent";

    /**
     *  Ивенты из окна запроса ресурсов.
     */
    String NEW_REQUEST_EVENT = "NewRequestEvent";
}
