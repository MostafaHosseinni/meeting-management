package ir.mine.project.base.authenticate.sec.authenticate.user.server.authentication;

import ir.mine.project.base.authenticate.sec.authenticate.user.server.user.BaseUser;
import ir.mine.project.base.excphndl.exceptionServer.BaseServerBusinessException;
import ir.mine.project.base.repository.BaseRepository;
import ir.mine.project.base.service.BaseServiceImpl;

public class AuthenticationService extends BaseServiceImpl implements
		IAuthenticationService {

	public AuthenticationService(BaseRepository baseRepository) {
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
