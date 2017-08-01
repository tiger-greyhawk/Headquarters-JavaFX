package name.timoshenko.communityhelper.server.controller.Security;

import name.timoshenko.communityhelper.server.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * На самом деле конфигуратор с http нам почти не нужен. Нужно конфигурировать через AuthenticationManagerBuilder.
 *
 * Аутентифицируемся через authenticationManager.  В authenticationProvider не заходим. Смотрим пункт 5.6.4 руководства Spring-Security.
 *
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends GlobalMethodSecurityConfiguration {

    @Autowired
    private final DataSource dataSource;

    @Autowired
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Configuration
    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure (HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .formLogin().loginPage("/view/login_window.fxml") // Ошибку не выдает, но и окно автоматом не создает
                .and()
                .authorizeRequests()
                .antMatchers("/", "/dolphin").permitAll()
                .anyRequest().authenticated();
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.passwordEncoder();
        // следующая строка почему-то не применяет алгоритм шифрования паролей при авторизации. Да и в любом случае надо это делать в UserDetailsService?
        //auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        //auth.authenticationProvider(authenticationProvider);
        auth.parentAuthenticationManager(authenticationManager);
    }

    @Bean
    public ShaPasswordEncoder passwordEncoder() throws Exception {
        return new ShaPasswordEncoder(256);
    }

    @Autowired
    public AuthenticationManagerDolphin authenticationManager;

    @Autowired
    UserDetailsServiceDolphin userDetailsService;

    @Autowired
    public UserRepository userRepository;

}