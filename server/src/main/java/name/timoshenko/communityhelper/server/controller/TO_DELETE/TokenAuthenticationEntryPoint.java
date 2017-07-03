package name.timoshenko.communityhelper.server.controller.TO_DELETE;

import name.timoshenko.communityhelper.server.controller.TO_DELETE.TokenAuthenticationHeaderNotFound;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Tiger on 30.06.2017.
 */
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (authException instanceof TokenAuthenticationHeaderNotFound) {
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, authException.getMessage());
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }
    }

}