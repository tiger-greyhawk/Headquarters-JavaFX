package name.timoshenko.communityhelper.server.model.service;

/**
 *
 */
public interface AuthService {
    boolean isAuthenticated(String login, String passwordHash);
}
