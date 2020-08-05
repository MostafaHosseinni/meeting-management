package ir.mine.project.base.authenticate.sec.authenticate.user.server.authentication;

import org.springframework.data.jpa.repository.JpaRepository;

import ir.mine.project.base.authenticate.sec.authenticate.user.server.user.BaseUser;
import ir.mine.project.base.excphndl.exceptionServer.BaseServerBusinessException;
import ir.mine.project.base.service.BaseServiceImpl;

public class AuthenticationService extends BaseServiceImpl implements
		IAuthenticationService {

	public AuthenticationService(JpaRepository baseRepository) {
		super(baseRepository);
	}

	@Override
	public String getCurrentUsername() throws BaseServerBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseUser getCurrentUser() throws BaseServerBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
