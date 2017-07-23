package name.timoshenko.communityhelper.server;

import com.canoo.platform.server.spring.DolphinPlatformApplication;
import javafx.application.Application;
import name.timoshenko.communityhelper.server.controller.Security.ACL.CustomApplicationInitializer;
import name.timoshenko.communityhelper.server.controller.Security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Основной класс, запускающий spring-framework, который, в свою очередь, запускает
 * приложение-сервер.
 * Является контейнером внедрения зависимостей, именно spring внедряет зависмости в
 * создаваемые объекты.
 */
@DolphinPlatformApplication
public class SpringServer extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(new Class[]{SpringServer.class}, args);
        //ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringServer.class, args);
//        ConfigurableApplicationContext applicationContext = SpringApplication.run(SecurityConfig.class, args);
        //applicationContext.registerShutdownHook();

    }




    /*@Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { CustomApplicationInitializer.class };
    }*/
}
