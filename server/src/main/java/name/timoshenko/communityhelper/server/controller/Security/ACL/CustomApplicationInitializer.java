package name.timoshenko.communityhelper.server.controller.Security.ACL;

import name.timoshenko.communityhelper.server.controller.Security.SecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 *
 */
public class CustomApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    /***
     * Фильтры, по идее инициализируются здесь. Но у меня они, вроде, инициализировались и без этого класса.
     * На всякий случай добавил, хотя и не понимаю как он внедряется (запускается, инициализируется). Магия Спринга?
     */
    public CustomApplicationInitializer() {
        super(SecurityConfig.class);
    }


}
