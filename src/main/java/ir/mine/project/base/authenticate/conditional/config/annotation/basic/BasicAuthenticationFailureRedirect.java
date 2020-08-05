package ir.mine.project.base.authenticate.conditional.config.annotation.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class BasicAuthenticationFailureRedirect extends
		SimpleUrlAuthenticationFailureHandler implements
		AuthenticationFailureHandler {

	private String baseUrl;

	public BasicAuthenticationFailureRedirect(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		if (exception.getClass().isAssignableFrom(
				UsernameNotFoundException.class))
			setDefaultFailureUrl(baseUrl + "?err=1");
		else if (exception.getClass().isAssignableFrom(
				BadCredentialsException.class))
			setDefaultFailureUrl(baseUrl + "?err=1");
		else if (exception.getClass().isAssignableFrom(DisabledException.class))
			setDefaultFailureUrl(baseUrl + "?err=2");
		else
			setDefaultFailureUrl(baseUrl + "?err=3");

		super.onAuthenticationFailure(request, response, exception);
	}

}
