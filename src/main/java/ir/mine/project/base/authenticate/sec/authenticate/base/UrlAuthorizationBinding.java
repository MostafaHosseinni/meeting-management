package ir.mine.project.base.authenticate.sec.authenticate.base;

import java.util.List;

public interface UrlAuthorizationBinding {
	
	List<RoleUrlAccessBinding> roleUrlAccessBindings();
	String[] permitAllUrls();
	String[] authenticatedUrls();

}
