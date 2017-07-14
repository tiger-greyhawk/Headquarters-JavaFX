package name.timoshenko.communityhelper.server.controller.Security;

import name.timoshenko.communityhelper.server.model.domain.User;
import name.timoshenko.communityhelper.server.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Configuration
@EnableWebSecurity
//@EnableWebMvc
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//public class SecurityConfig extends SecurityConfigurerAdapter {


/***
 * На самом деле конфигуратор с http нам почти не нужен. Нужно конфигурировать через AuthenticationManagerBuilder.
 *
 * Аутентифицируемся через authenticationManager.  В authenticationProvider не заходим. Не понимаю пока систему конфигурации.
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()

                .authorizeRequests()
                .antMatchers("/", "/dolphin").permitAll()
                //.antMatchers("/dolphin/view/*").permitAll()//.hasAnyRole("ROLE_USER")
                //.antMatchers("/dolphin/media/**").hasAnyRole("ROLE_ADMIN")
                .anyRequest().authenticated();
    }



    @Override
    //@Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("user1").password("123").roles("USER");
        //auth.jdbcAuthentication()
        auth.userDetailsService(userDetailsService());
        auth.authenticationProvider(authenticationProvider);
        auth.parentAuthenticationManager(authenticationManager);
    }

    @Autowired
    AuthenticationProviderDolphin authenticationProvider;

    @Autowired
    AuthenticationManagerDolphin authenticationManager;

    @Bean(name = "userDetailsService")
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceDolphin();
    }
}