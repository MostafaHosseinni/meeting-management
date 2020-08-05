package ir.mine.project.base.authenticate.sec.authenticate.config.initializer;

import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer
{
	
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		super.beforeSpringSecurityFilterChain(servletContext);
	}
	
}
