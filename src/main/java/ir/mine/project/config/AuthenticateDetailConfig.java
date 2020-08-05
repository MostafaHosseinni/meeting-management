package ir.mine.project.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import ir.mine.project.base.authenticate.conditional.config.annotation.EnableAuthenticationMechanism;
import ir.mine.project.base.authenticate.conditional.config.annotation.basic.BasicErrorNavigatorHandler;
import ir.mine.project.base.authenticate.sec.authenticate.base.BasicAuthenticationConfigurer;
import ir.mine.project.base.authenticate.sec.authenticate.base.ErrorUrlAfterFailedAuthentication;
import ir.mine.project.base.authenticate.sec.authenticate.base.HomeUrlAfterSuccessfulAuthentication;
import ir.mine.project.base.authenticate.sec.authenticate.base.RoleUrlAccessBinding;
import ir.mine.project.base.authenticate.sec.authenticate.base.UrlAuthorizationBinding;
import ir.mine.project.base.authenticate.sec.authenticate.conditional.AuthenticationType;
import ir.mine.project.base.authenticate.sec.authenticate.config.SecAuthenticateConfig;

@Configuration
@Import(SecAuthenticateConfig.class)
@EnableAuthenticationMechanism(AuthenticationType.BASIC)
public class AuthenticateDetailConfig implements BasicAuthenticationConfigurer {

	@Override
	public String accessDeniedPageUrl() {
		return "/form/codePage/403.html";
	}

	@Override
	public String logoutUrl() {
		return "/form/logout.html";
	}

	@Override
	public String sessionExpiredUrl() {
		return "/form/sessionexpired.html";
	}

	@Override
	public HomeUrlAfterSuccessfulAuthentication homeURL() {
		return new HomeUrlAfterSuccessfulAuthentication() {
			@Override
			public String decideHomeUrlAfterSuccessfulAuthentication(Authentication authentication) {

				Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
				if (authorities != null) {
					for (GrantedAuthority grantedAuthority : authorities) {
						String authority = grantedAuthority.getAuthority();
//
// 						if (RoleNames.ROLE_ADMIN.equals(authority))
//							return "/form/admin1.html";
					}
				}

				return "/index.html";
			}
		};
	}

	@Override
	public UrlAuthorizationBinding urlAuthorizationBinding() {
		return new UrlAuthorizationBinding() {
			@Override
			public List<RoleUrlAccessBinding> roleUrlAccessBindings() {
				List<RoleUrlAccessBinding> list = new ArrayList<RoleUrlAccessBinding>();
				return list;
			}

			@Override
			public String[] authenticatedUrls() {
				return new String[] { "/TransactionResource/**" };
			}

			@Override
			public String[] permitAllUrls() {
				// TODO set security
				return new String[] { "/form/**", "/js/**", "/assets/**", "/app/**",
						"/TransactionResource/SimplePayment", "/TransactionResource/**", "/NS/**", "login/",
						"/Login/**" };
			}
		};
	}

	@Override
	public String loginUrl() {
		return "/form/login.html";
	}

	@Override
	public String loginProcessorUrl() {
		return null;
	}

	@Override
	public ErrorUrlAfterFailedAuthentication errorUrl() {
		return new BasicErrorNavigatorHandler();
	}

}
