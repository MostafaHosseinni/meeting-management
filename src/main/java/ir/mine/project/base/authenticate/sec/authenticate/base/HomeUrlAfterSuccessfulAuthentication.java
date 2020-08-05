package ir.mine.project.base.authenticate.sec.authenticate.base;

import org.springframework.security.core.Authentication;

public interface HomeUrlAfterSuccessfulAuthentication {

	String decideHomeUrlAfterSuccessfulAuthentication(Authentication authentication);

}
