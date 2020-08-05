package ir.mine.project.base.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import ir.mine.project.base.authorization.permission.WebPermissionEvaluator;

@Configuration
//@EnableWebSecurity
@ComponentScan(basePackages = "payam.avaran.backend")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecAuthorizationConfig extends GlobalMethodSecurityConfiguration {

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
        handler.setPermissionEvaluator(webPermissionEval());
        return handler;
    }

    @Bean
    public WebPermissionEvaluator webPermissionEval() {
        return new WebPermissionEvaluator();
    }

//	@Bean
//	public BasicUserURLAuthorityProvider getBasicUserPermission() {
//		return new BasicUserURLAuthorityProvider();
//	}

}
