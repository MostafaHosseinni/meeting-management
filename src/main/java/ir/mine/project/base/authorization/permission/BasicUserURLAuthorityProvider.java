package ir.mine.project.base.authorization.permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ir.mine.project.base.authenticate.sec.authenticate.user.server.operation.Operation;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.permissions.IUserPermissionsProvider;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.permissions.PermissionRoleDTO;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.role.Role;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.user.BaseUser;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.user.BaseUserService;
import ir.mine.project.base.excphndl.exceptionServer.BaseServerBusinessException;

@Component
public class BasicUserURLAuthorityProvider implements IUserPermissionsProvider {

    BaseUserService userApi;

    @Autowired
    public void setUserApi(BaseUserService userApi) {
        this.userApi = userApi;
    }

    @Override
    @Transactional
    public PermissionRoleDTO getUserAuthorities(String currentUsername)
            throws BaseServerBusinessException {

        BaseUser userEntity = userApi.findFirstByUsername(currentUsername);

        List<String> roleNameList = new ArrayList<String>();
        List<String> operationNameList = new ArrayList<String>();

        if (userEntity != null) {
            Set<Role> roles = userEntity.getRoles();
            if (roles != null && !roles.isEmpty()) {
                for (Role role : roles) {
                    roleNameList.add(role.getName());
                    Set<Operation> operations = role.getOperations();
                    if (operations != null) {
                        for (Operation operationEntity : operations) {
                            String operationTreeName = operationEntity
                                    .getName();
                            operationNameList.add(operationTreeName);
                        }
                    }
                }
            }

        }

        return new PermissionRoleDTO(roleNameList, operationNameList);
    }

}
