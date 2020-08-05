package ir.mine.project.base.authenticate.sec.authenticate.base;

import java.util.Map;

public interface ErrorUrlAfterFailedAuthentication {

	Map<String, String> decideErrorUrlAfterFailedAuthentication(String baseUrl);

}
