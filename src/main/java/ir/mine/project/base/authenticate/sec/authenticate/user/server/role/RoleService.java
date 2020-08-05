package ir.mine.project.base.authenticate.sec.authenticate.user.server.role;

import java.util.List;

import ir.mine.project.base.excphndl.exceptionServer.BaseServerBusinessException;
import ir.mine.project.base.service.BaseService;

/**
 * Service Interface for managing Role.
 */
public interface RoleService extends BaseService<Role, Long> {

    List<Role> findByName(String name);

    List<Role> getUserRole() throws BaseServerBusinessException;
    
    Role findOneByName(String name);
}
