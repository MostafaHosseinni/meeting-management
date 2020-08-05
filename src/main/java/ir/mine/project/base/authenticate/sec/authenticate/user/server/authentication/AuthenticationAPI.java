package ir.mine.project.base.authenticate.sec.authenticate.user.server.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import ir.mine.project.base.authenticate.sec.authenticate.user.server.user.BaseUser;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.user.BaseUserService;
import ir.mine.project.base.excphndl.exceptionServer.BaseServerBusinessException;

@Service
public class AuthenticationAPI /* extends BaseServiceImpl */ implements IAuthenticationAPI {

	@Autowired
	BaseUserService baseUserAPI;

	@Override
	public String getCurrentUsername() throws BaseServerBusinessException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserDetails) {
				UserDetails ud = (UserDetails) principal;
				return ud.getUsername();
			}
		}
		return null;
	}

	@Override
	public BaseUser getCurrentUser() throws BaseServerBusinessException {

		String currentUsername = getCurrentUsername();
		BaseUser baseUserEntity = baseUserAPI.findFirstByUsername(currentUsername);
		if (baseUserEntity != null) {
			return baseUserEntity;
		}

		return null;
	}

	@Override
	public String getSessionId() throws BaseServerBusinessException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object details = authentication.getDetails();
			if (details instanceof WebAuthenticationDetails) {
				return ((WebAuthenticationDetails) details).getSessionId();
			}
		}
		return null;
	}

}
