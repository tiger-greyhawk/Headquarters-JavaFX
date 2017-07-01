package name.timoshenko.communityhelper.server.controller.Security;

import name.timoshenko.communityhelper.common.model.CurrentUserModel;
import name.timoshenko.communityhelper.server.model.dao.dummy.DummyUserDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Created by Tiger on 30.06.2017.
 */
@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //@Value("#{.tokenAuthentication.header}")
    //private String header;

    //@Value("${application.tokenAuthentication.ignoreFault}")
    //private boolean ignoreFault;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests().antMatchers("/", "/dolphin").permitAll();
        //http.anonymous().authenticationProvider(tokenAuthenticationProvider(myUserDetailsService()));
        //http.headers().defaultsDisabled();
        //http.antMatcher("/dolphin").csrf().disable();
        http.csrf().disable();
        //http.userDetailsService(myUserDetailsService());
        /*http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //.authorizeRequests().anyRequest().authenticated()
                //.and()
                //.httpBasic().disable()
                //.formLogin().disable()
                .csrf().disable()
                .addFilterBefore(authenticationFilter(authenticationManager()), BasicAuthenticationFilter.class);*/
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(myUserDetailsService());
                //.withUser("user").password("password").roles("USER")
                //.and()
                //.withUser("admin").password("adminPassword").roles("USER", "ADMIN");
        //auth.authenticationProvider(authenticationProvider(auth.getDefaultUserDetailsService()));
        //AuthenticationDolphin authenticationDolphin = new AuthenticationDolphin(myUserDetailsService());
        //authenticationDolphin().getPrincipal();
    }

    /*@Bean
    public AuthenticationEntryPoint tokenAuthenticationEntryPoint() {
        return new TokenAuthenticationEntryPoint();
    }*/

    /*@Bean
    public AuthenticationManager authenticationProvider() {
        return new AuthenticationManagerDolphin();
    }*/

    /*@Bean
    public AuthenticationFilter authenticationFilter(AuthenticationManager authenticationManager) {
        //header = "User1:123";
        //ignoreFault = true;
        return new AuthenticationFilter(authenticationManager);//, tokenAuthenticationEntryPoint());//, header, ignoreFault);
    }*/

    /*@Bean
    public AuthenticationDolphin authenticationDolphin(){
        return new AuthenticationDolphin(myUserDetailsService());
    }*/


    /*@Bean UserDetailsServiceDolphin myUserDetailsService()
    {
        return new UserDetailsServiceDolphin(new DummyUserDao());
    }*/
}