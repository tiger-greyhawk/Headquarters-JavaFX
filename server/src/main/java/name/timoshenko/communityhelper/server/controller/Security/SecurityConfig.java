package name.timoshenko.communityhelper.server.controller.Security;

import name.timoshenko.communityhelper.server.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
 * Для реализации ACL:
 * Старые файл (скорее всего не нужны): CustomApplicationInitializer, InMemoryAclServiceImpl, SimpleAclImpl, UserExtendedPermission, UserNameRetrievalStrategy
 */
@Configuration
//@EnableWebSecurity
//@EnableWebMvc
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//@EnableJpaRepositories(value = "name.timoshenko.communityhelper.server.model.repositories", repositoryFactoryBeanClass = AclJpaRepositoryFactoryBean.class)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
public class SecurityConfig extends GlobalMethodSecurityConfiguration {

    @Autowired
    private final DataSource dataSource;

    /*@Bean
    public EmbeddedDatabase dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                //as your data.sql file contains both DDL & DML you might want to rename it (e.g. init.sql)
                .addScript("classpath:/data-h2.sql")
                .build();
    }*/



    @Autowired
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    /*public SecurityConfig() {

    }*/

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .formLogin().loginPage("/view/login_window.fxml") // Ошибку не выдает, но и окно автоматом не создает
                .and()
                .authorizeRequests()
                .antMatchers("/", "/dolphin").permitAll()
                .anyRequest().authenticated();
    }*/

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
        //auth.inMemoryAuthentication().withUser("user1").password("123").roles("USER");
        //auth.jdbcAuthentication().dataSource(dataSource);

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


    /***
     * Дальше идет закомментированный код попыток реализации Spring Security ACL. Оно частично работает, но, учитывая
     * что это через jdbc, а у нас используется JPA - глючит.
     */


    /*private static final String ROLE_PREFIX = "";
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(ROLE_PREFIX);
    }*/

    /*@Bean
    public AclEntryVoter aclEntryVoter() {
        String processConfigAttribute = "ACL_DELETE"; //Const.ACL_DELETE;
        Permission[] requirePermission = new Permission[2];
        requirePermission[0] = org.springframework.security.acls.domain.BasePermission.ADMINISTRATION;
        requirePermission[1] = org.springframework.security.acls.domain.BasePermission.READ;
        AclEntryVoter aclEntryVoter = new AclEntryVoter(aclService(),
                processConfigAttribute, requirePermission);
        aclEntryVoter.setProcessDomainObjectClass(Player.class);
        return aclEntryVoter;
    }*/

    /*@Bean
    public AclService aclService() {
        return new JdbcMutableAclService(dataSource, lookupStrategy(), aclCache());
    }*/


    /*@Bean
    public MutableAclService aclService(){
        JdbcMutableAclService service = new JdbcMutableAclService(dataSource, lookupStrategy(), aclCache());
        service.setClassIdentityQuery("select currval(pg_get_serial_sequence('acl_class', 'id'))");
        service.setSidIdentityQuery("select currval(pg_get_serial_sequence('acl_sid', 'id'))");
        return service;
    }*/


    /*@Bean
    public LookupStrategy lookupStrategy()
    {
        return new BasicLookupStrategy(dataSource, aclCache(),
                aclAuthorizationStrategy(), new ConsoleAuditLogger());
    }*/

    /*@Bean(name = "aclCache")
    public EhCacheBasedAclCache aclCache()
    {

        return new EhCacheBasedAclCache(aclEhCache().getObject(),
                permissionGrantingStrategy(), aclAuthorizationStrategy());
    }*/

    /*@Bean
    public PermissionGrantingStrategy permissionGrantingStrategy(){
        return new DefaultPermissionGrantingStrategy(
                new ConsoleAuditLogger());
    }*/

    /*@Bean(name = "aclEhCache")
    public EhCacheFactoryBean aclEhCache()
    {
        EhCacheFactoryBean factoryBean = new EhCacheFactoryBean();
        EhCacheManagerFactoryBean cacheManager = new EhCacheManagerFactoryBean();
        cacheManager.setConfigLocation(new ClassPathResource("cache/ehcache.xml"));
        factoryBean.setCacheName("aclCache");
        factoryBean.setCacheManager(cacheManager.getObject());
        return factoryBean;
    }*/

    /*@Bean(name = "adminRole")
    public SimpleGrantedAuthority adminRole()
    {
        return new SimpleGrantedAuthority("ROLE_ADMIN");
    }

    @Bean(name = "userRole")
    public SimpleGrantedAuthority userRole()
    {
        return new SimpleGrantedAuthority("ROLE_USER");
    }

    @Bean
    public AclAuthorizationStrategy aclAuthorizationStrategy()
    {
        return new AclAuthorizationStrategyImpl(adminRole(), adminRole(), adminRole());
    }*/

    /*@Bean
    public PermissionEvaluator permissionEvaluator() {
        return new AclPermissionEvaluator(aclService());
    }*/

    /*@Override
    public MethodSecurityExpressionHandler createExpressionHandler(){
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();

        expressionHandler.setPermissionEvaluator(new AclPermissionEvaluator(aclService()));
        expressionHandler.setPermissionCacheOptimizer(new AclPermissionCacheOptimizer(aclService()));
        return expressionHandler;
    }*/

    /*@Bean
    public DefaultMethodSecurityExpressionHandler expressionHandler() {
        return new DefaultMethodSecurityExpressionHandler();
    }*/
    //@Autowired
    //AuthenticationProviderDolphin authenticationProvider;

    //@Autowired
    //public AuthenticationManager authenticationManager;

    //@Autowired
/*    @Bean(name = "authenticationManager")
    public AuthenticationManager authenticationManager() {
        return new AuthenticationManagerDolphin(userDetailsService());
    }

    @Bean(name = "userDetailsService")
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceDolphin(userRepository);
    }
*/

}