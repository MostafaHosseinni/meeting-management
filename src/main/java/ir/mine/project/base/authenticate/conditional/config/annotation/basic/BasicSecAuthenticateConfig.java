package ir.mine.project.base.authenticate.conditional.config.annotation.basic;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.CollectionUtils;

import ir.mine.project.base.Util.hash.HashUtil;
import ir.mine.project.base.authenticate.sec.authenticate.base.BasicAuthenticationConfigurer;
import ir.mine.project.base.authenticate.sec.authenticate.base.DefaultUrlAuthorizationBinding;
import ir.mine.project.base.authenticate.sec.authenticate.base.ErrorUrlAfterFailedAuthentication;
import ir.mine.project.base.authenticate.sec.authenticate.base.HomeUrlAfterSuccessfulAuthentication;
import ir.mine.project.base.authenticate.sec.authenticate.base.RoleUrlAccessBinding;
import ir.mine.project.base.authenticate.sec.authenticate.base.UrlAuthorizationBinding;
import ir.mine.project.base.authenticate.sec.authenticate.config.authsuccess.AuthorizationAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class BasicSecAuthenticateConfig extends WebSecurityConfigurerAdapter {

    private UrlAuthorizationBinding urlAuthorizationBinding = new DefaultUrlAuthorizationBinding();
    private HomeUrlAfterSuccessfulAuthentication targetUrlResolver = new HomeUrlAfterSuccessfulAuthentication() {
        @Override
        public String decideHomeUrlAfterSuccessfulAuthentication(
                Authentication authentication) {
            return "/";
        }
    };
    private ErrorUrlAfterFailedAuthentication errorUrlResolver = null;
    private String accessDeniedUrl = "/form/403.html";
    private String loginPageUrl = "/form/login.html";
    private String loginProcessorUrl = "/login.do";
    private String logoutPageUrl = "/form/logout.html";
    private String sessionExpiredUrl = "/form/sessionexpired.html";
    private String statisticsUrl = "/webservice/rest/Statistics/Datasource/getInfo";


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // This is here to ensure that the static content (JavaScript, CSS, etc)
        // is accessible from the login page without authentication
        //TODO encode password
        auth.userDetailsService(userModelApi()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return HashUtil.sha1Hash(charSequence.toString());//passwordEncoder().encode(charSequence.toString());
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                //    String encode = passwordEncoder().encode(charSequence);
            	boolean equals = HashUtil.sha1Hash(charSequence.toString()).equals(s);
                return equals;
            }
        });
    }

    @Bean
    public AuthenticationSuccessHandler basicAuthenticationSuccessHandler() {
        AuthorizationAuthenticationSuccessHandler successHandler = new AuthorizationAuthenticationSuccessHandler();
        successHandler.setTargetUrlResolver(targetUrlResolver);
        successHandler.setSendRedirect(Boolean.TRUE);
        return successHandler;
    }

    @Bean(name = "basicAuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement().maximumSessions(-1);

        List<RoleUrlAccessBinding> roleUrlAccessBindings = urlAuthorizationBinding
                .roleUrlAccessBindings();
        String[] permitAllUrls = urlAuthorizationBinding.permitAllUrls();
        String[] authenticatedUrls = urlAuthorizationBinding
                .authenticatedUrls();

        ExceptionHandlingConfigurer<HttpSecurity> accessDeniedPart = http
                .exceptionHandling()
                .accessDeniedPage(accessDeniedUrl)
                .authenticationEntryPoint(
                        new AjaxAwareAuthenticationEntryPoint(loginPageUrl));
        HttpSecurity httpSecurityAfterAccessDenied = accessDeniedPart.and();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = httpSecurityAfterAccessDenied
                .authorizeRequests();

        authorizeRequests.antMatchers(sessionExpiredUrl).permitAll();
        authorizeRequests.antMatchers(accessDeniedUrl).permitAll();
        authorizeRequests.antMatchers(loginPageUrl).permitAll();
        authorizeRequests.antMatchers(statisticsUrl).permitAll();

        if (permitAllUrls != null) {
            authorizeRequests.antMatchers(permitAllUrls).permitAll();
        }

        if (roleUrlAccessBindings != null) {
            for (RoleUrlAccessBinding roleUrl : roleUrlAccessBindings) {
                authorizeRequests.antMatchers(roleUrl.getUrl())
                        .hasAnyAuthority(roleUrl.getRole());
            }
        }

        if (authenticatedUrls != null) {
            if (authenticatedUrls.length == 1
                    && "ALL".equals(authenticatedUrls[0])) {
                authorizeRequests.anyRequest().authenticated();
            } else {
                authorizeRequests.antMatchers(authenticatedUrls)
                        .authenticated();
            }
        }

        authorizeRequests
                .and()
                .csrf()
                .disable()
                .formLogin()
                .loginPage(loginPageUrl)
                // .loginProcessingUrl(loginProcessorUrl)
                .permitAll()
                .successHandler(basicAuthenticationSuccessHandler())
                .failureHandler(basicAuthenticationFailureRedirect())
                .usernameParameter("username").passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(logoutPageUrl)).logoutSuccessUrl(loginPageUrl).permitAll()
                //.logoutSuccessUrl(logoutPageUrl).permitAll()
                // .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true).and().sessionManagement()
                .maximumSessions(1).expiredUrl(sessionExpiredUrl);

        // .sessionManagement().invalidSessionUrl("/login?time=1")
        // .maximumSessions(1)
        ;
    }

    @Bean
    public UserDetailsService userModelApi() {
        return new BasicUserDetailService();
    }

    @Bean
    public AuthenticationFailureHandler basicAuthenticationFailureRedirect() {
        // return new BasicAuthenticationFailureRedirect(loginPageUrl);
        ExceptionMappingAuthenticationFailureHandler handler = new ExceptionMappingAuthenticationFailureHandler();
        if (errorUrlResolver != null) {
            handler.setExceptionMappings(errorUrlResolver
                    .decideErrorUrlAfterFailedAuthentication(loginPageUrl));
        }
        return handler;
    }

    @Autowired(required = false)
    void setConfigurers(Collection<BasicAuthenticationConfigurer> configurers) {
        if (CollectionUtils.isEmpty(configurers)) {
            return;
        }
        if (configurers.size() > 1) {
            throw new IllegalStateException(
                    "Only one BasicAuthenticationConfigurer may exist");
        }
        BasicAuthenticationConfigurer configurer = configurers.iterator()
                .next();
        this.targetUrlResolver = configurer.homeURL();
        this.errorUrlResolver = configurer.errorUrl();
        this.urlAuthorizationBinding = configurer.urlAuthorizationBinding();
        this.loginProcessorUrl = configurer.loginProcessorUrl();
        this.loginPageUrl = configurer.loginUrl();
        this.accessDeniedUrl = configurer.accessDeniedPageUrl();
        this.logoutPageUrl = configurer.logoutUrl();
        this.sessionExpiredUrl = configurer.sessionExpiredUrl();
    }

}
