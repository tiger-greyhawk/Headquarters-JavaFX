package name.timoshenko.communityhelper.server.controller.Security.SortOutAcl;

/**
 *
 */
public interface MyPermissionChecker {
    boolean checkPermission(Long factionId);
    void setPermission();
}
