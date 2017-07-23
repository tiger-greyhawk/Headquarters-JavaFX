package name.timoshenko.communityhelper.server.controller.Security.SortOutAcl;

/**
 *
 */
public interface MyPermission {
    boolean checkPermission(int factionId);
    void setPermission();
}
