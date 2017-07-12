package name.timoshenko.communityhelper.server.controller.TO_DELETE;

import com.google.common.base.Strings;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.GenericFilterBean;

//import javax.security.sasl.AuthenticationException;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public class AuthenticationFilter //extends GenericFilterBean
{
/*
    private final AuthenticationManager authenticationManager;

    //private final AuthenticationEntryPoint authenticationEntryPoint;

    //private final String header;

    //private final boolean ignoreFault;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {//, AuthenticationEntryPoint authenticationEntryPoint) {//, String header, boolean ignoreFault) {
        this.authenticationManager = authenticationManager;
        //this.authenticationEntryPoint = authenticationEntryPoint;
        //this.header = header;
        //this.ignoreFault = ignoreFault;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        try {

            //if (Strings.isNullOrEmpty(header)) {
                //throw new TokenAuthenticationHeaderNotFound("Header " + header + " is not found.", null);
            //}
            //String headerValue = httpServletRequest.getHeader(header);
            //Authentication authentication = authenticationManager.authenticate(new TokenAuthentication(headerValue));
            //Authentication authentication = authenticationManager.authenticate();

            //SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(servletRequest, servletResponse);
        } catch (AuthenticationException authenticationException) {
            //if (!ignoreFault) {
                //authenticationEntryPoint.commence(httpServletRequest, httpServletResponse, authenticationException);
            //} else {
                //filterChain.doFilter(servletRequest, servletResponse);
            //}
        }
    }*/
}