package ir.mine.project.base.authenticate.sec.authenticate.user.server.user;

import java.util.List;

import ir.mine.project.base.authenticate.sec.authenticate.user.server.operation.Operation;
import ir.mine.project.base.authenticate.sec.authenticate.user.shared.dto.BaseUserDTO;
import ir.mine.project.base.excphndl.exceptionServer.BaseServerBusinessException;

public interface IBaseUserAPI /*, UserDetailsService*/ {

    String USERNAME_SESSION_PROPERTY_NAME = "currentUsername";


    List<BaseUser> findByUsername(String username);

    BaseUser findFirstByUsername(String username);

    List<Operation> getOperationsUser() throws BaseServerBusinessException;

    BaseUser getCurrentUser() throws BaseServerBusinessException;

    BaseUserDTO findUserWithRoleAndOperation(Long id);

}
