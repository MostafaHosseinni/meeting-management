package ir.mine.project.base.authenticate.sec.authenticate.base;

public interface CasAuthenticationConfigurer extends
		BaseAuthenticationConfigurer {

	String casUrl();

	String applicationUrl();

	String applicationName();
	
	String casFailedUrl();

}
