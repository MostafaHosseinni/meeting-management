package ir.mine.project.base.authenticate.sec.authenticate.base;

public interface BaseAuthenticationConfigurer {

	String accessDeniedPageUrl();

	String logoutUrl();

	String sessionExpiredUrl();

	HomeUrlAfterSuccessfulAuthentication homeURL();
	
	ErrorUrlAfterFailedAuthentication errorUrl();

	UrlAuthorizationBinding urlAuthorizationBinding();
}
