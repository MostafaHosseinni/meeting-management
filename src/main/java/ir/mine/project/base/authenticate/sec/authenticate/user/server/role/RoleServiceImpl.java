package ir.mine.project.base.authenticate.sec.authenticate.user.server.role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.mine.project.base.authenticate.sec.authenticate.user.server.operationinfo.OperationDescription;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.operationinfo.OperationGroupName;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.user.BaseUser;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.user.IBaseUserAPI;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityModel;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityOperation;
import ir.mine.project.base.excphndl.exceptionServer.BaseServerBusinessException;
import ir.mine.project.base.service.BaseServiceImpl;

/**
 * Service Implementation for managing Role.
 */
@Service("RoleServiceImpl")
@Transactional
@SecurityModel(operations = {
		@SecurityOperation(operationName = "role:create", operationEntityName = "Role", displayName = OperationDescription.roleC, oprationGroup = OperationGroupName.role),
		@SecurityOperation(operationName = "role:read", operationEntityName = "Role", displayName = OperationDescription.roleR, oprationGroup = OperationGroupName.role),
		@SecurityOperation(operationName = "role:update", operationEntityName = "Role", displayName = OperationDescription.roleU, oprationGroup = OperationGroupName.role),
		@SecurityOperation(operationName = "role:delete", operationEntityName = "Role", displayName = OperationDescription.roleD, oprationGroup = OperationGroupName.role), })

public class RoleServiceImpl extends BaseServiceImpl<Role, Long, RoleRepository> implements RoleService {

	private final IBaseUserAPI baseUserAPI;

	public RoleServiceImpl(RoleRepository roleRepository, IBaseUserAPI baseUserAPI) {
		super(roleRepository);
		this.baseUserAPI = baseUserAPI;
	}

	@Override
	public List<Role> findByName(String name) {
		return baseRepository.findByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Role> getUserRole() throws BaseServerBusinessException {

		BaseUser currentUser = baseUserAPI.getCurrentUser();
		if (currentUser.getSuperUser() != null && currentUser.getSuperUser())
			return baseRepository.findAll();
		else
			return new ArrayList<>(currentUser.getRoles());
	}

	@Override
	public Role findOneByName(String name) {
		return baseRepository.findOneByName(name);
	}

}
