package ir.mine.project.base.authenticate.sec.authenticate.user.server.authentication;

import ir.mine.project.base.authenticate.sec.authenticate.user.server.user.BaseUser;
import ir.mine.project.base.excphndl.exceptionServer.BaseServerBusinessException;
import ir.mine.project.base.service.BaseService;

public interface IAuthenticationService extends BaseService {

    String getCurrentUsername() throws BaseServerBusinessException;

    BaseUser getCurrentUser() throws BaseServerBusinessException;

}
