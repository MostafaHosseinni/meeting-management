package ir.mine.project.base.authorization.permission;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import ir.mine.project.base.authenticate.sec.authenticate.base.BaseUserDetail;
import ir.mine.project.base.excphndl.exceptionServer.BaseServerBusinessException;

public class WebPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Object targetDomainObject, Object permission) {
        try {
            String simpleName = targetDomainObject == null ? ""
                    : targetDomainObject.toString() + ":";
            String operationTreeName = simpleName + permission.toString();

            // ServletRequestAttributes webRequest = (ServletRequestAttributes)
            // RequestContextHolder
            // .currentRequestAttributes();
            // UserEntity userEntity = (UserEntity) webRequest.getRequest()
            // .getSession()
            // .getAttribute(IUserApi.USER_SESSION_PROPERTY_NAME);
            // if (userEntity == null) {
            // throw new BaseServerBusinessException("User not found");
            // }
            // if (userEntity.getSuperUser() != null &&
            // userEntity.getSuperUser()) {
            // return true;
            // }
            // Set<RoleEntity> roles = (Set<RoleEntity>) webRequest.getRequest()
            // .getSession()
            // .getAttribute(IUserApi.ROLES_SESSION_PROPERTY_NAME);
            // if (roles == null || roles.isEmpty()) {
            // throw new BaseServerBusinessException("Access Denied");
            // }
            // Set<OperationEntity> operations = (Set<OperationEntity>)
            // webRequest
            // .getRequest().getSession()
            // .getAttribute(IUserApi.OPERATIONS_SESSION_PROPERTY_NAME);

            BaseUserDetail detail = (BaseUserDetail) authentication
                    .getPrincipal();
            List<String> operations = detail.getUserOperations();

            if (operations != null && !operations.isEmpty()) {
                for (String operation : operations) {
                    if (operationTreeName.startsWith(operation)) {
                        return true;
                    }
                }
            }
            throw new BaseServerBusinessException("Access Denied");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId, String targetType, Object permission) {
        // TODO Auto-generated method stub
        return false;
    }

}
