package name.timoshenko.communityhelper.server;

import com.canoo.platform.server.spring.DolphinPlatformApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Основной класс, запускающий spring-framework, который, в свою очередь, запускает
 * приложение-сервер.
 * Является контейнером внедрения зависимостей, именно spring внедряет зависмости в
 * создаваемые объекты.
 */
@DolphinPlatformApplication
public class SpringServer extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{SpringServer.class}, args);
        //ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringServer.class, args);
        //applicationContext.registerShutdownHook();
    }

}
