package ir.mine.project.base.authenticate.sec.authenticate.user.server.permissions;

import ir.mine.project.base.excphndl.exceptionServer.BaseServerBusinessException;

public interface IUserPermissionsProvider {
    PermissionRoleDTO getUserAuthorities(String username) throws BaseServerBusinessException;
}
