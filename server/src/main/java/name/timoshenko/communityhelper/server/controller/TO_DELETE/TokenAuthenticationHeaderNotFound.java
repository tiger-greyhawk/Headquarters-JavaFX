package name.timoshenko.communityhelper.server.controller.TO_DELETE;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Tiger on 30.06.2017.
 */
public class TokenAuthenticationHeaderNotFound extends AuthenticationException {

    public TokenAuthenticationHeaderNotFound(String msg, Throwable t) {
        super(msg, t);
    }

}