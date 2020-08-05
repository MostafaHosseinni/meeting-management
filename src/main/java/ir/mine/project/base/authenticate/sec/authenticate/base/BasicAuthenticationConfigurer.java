package ir.mine.project.base.authenticate.sec.authenticate.base;



public interface BasicAuthenticationConfigurer extends BaseAuthenticationConfigurer{
	
	String loginUrl();
	String loginProcessorUrl();
}
