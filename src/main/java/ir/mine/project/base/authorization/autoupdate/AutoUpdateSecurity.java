package ir.mine.project.base.authorization.autoupdate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import ir.mine.project.base.Util.hash.HashUtil;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.operation.Operation;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.operation.OperationService;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.role.Role;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.role.RoleService;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.user.BaseUser;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.user.BaseUserService;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityModel;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityOperation;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityRole;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityUser;
import ir.mine.project.base.authorization.autoupdate.listener.IUserChangeListener;
import ir.mine.project.base.excphndl.exceptionServer.BaseServerBusinessException;

@Component
public class AutoUpdateSecurity extends ClassPathScanningCandidateComponentProvider {

	private OperationService operationApi;
	private RoleService roleApi;
	private BaseUserService userApi;
	private PlatformTransactionManager transactionManager;
	private IUserChangeListener userChangeListener;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public AutoUpdateSecurity() {
		super(false);
		addIncludeFilter(new AnnotationTypeFilter(SecurityModel.class));
	}

	@PostConstruct
	public void initialize() {
		TransactionTemplate template = new TransactionTemplate(transactionManager);
		template.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				Set<BeanDefinition> findCandidateComponents = findCandidateComponents("ir.mine.project.service.impl");
				for (BeanDefinition beanDefinition : findCandidateComponents) {
					String beanClassName = beanDefinition.getBeanClassName();
					try {
						Class<?> beanClass = Class.forName(beanClassName);
						SecurityModel securityModel = beanClass.getAnnotation(SecurityModel.class);
						SecurityOperation[] operations = securityModel.operations();
						for (int i = 0; i < operations.length; i++) {
							saveOrUpdateOperation(operations[i]);
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
				findCandidateComponents = findCandidateComponents("ir.mine.project");
				for (BeanDefinition beanDefinition : findCandidateComponents) {
					String beanClassName = beanDefinition.getBeanClassName();
					try {
						Class<?> beanClass = Class.forName(beanClassName);
						SecurityModel securityModel = beanClass.getAnnotation(SecurityModel.class);
						SecurityOperation[] operations = securityModel.operations();
						for (int i = 0; i < operations.length; i++) {
							saveOrUpdateOperation(operations[i]);
						}
						SecurityRole[] roles = securityModel.roles();
						for (int i = 0; i < roles.length; i++) {
							saveOrUpdateRole(roles[i]);
						}
						SecurityUser[] users = securityModel.users();
						for (int i = 0; i < users.length; i++) {
							saveOrUpdateUser(users[i]);
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	private void saveOrUpdateUser(SecurityUser securityUser) {
		try {
			BaseUser example = new BaseUser();
			example.setUserName(securityUser.username());
			List<BaseUser> resultList = userApi.findByUsername(securityUser.username());
			if (resultList == null || resultList.isEmpty()) {
				getUser(securityUser, example);
				userApi.saveNotSecure(example);
				if (userChangeListener != null) {
					userChangeListener.userSaved(example);
				}
			} else {
				BaseUser dbUser = resultList.get(0);
				Set<Role> dbRoles = dbUser.getRoles();
				Set<String> dbRoleNames = new HashSet<String>();
				for (Role roleEntity : dbRoles) {
					dbRoleNames.add(roleEntity.getName());
				}
				boolean rolesAreEqual = true;
				if (securityUser.roles().length == dbRoleNames.size()) {
					for (String dbRoleName : dbRoleNames) {
						if (Arrays.binarySearch(securityUser.roles(), dbRoleName) == -1) {
							rolesAreEqual = false;
						}
					}
				} else {
					rolesAreEqual = false;
				}
				if (!securityUser.superUser() == dbUser.getSuperUser().booleanValue()
						|| !HashUtil.sha1Hash(securityUser.password()).equals(dbUser.getPassword()) || !rolesAreEqual) {
					getUser(securityUser, dbUser);
//					userApi.updateNotSecured(dbUser);
					if (userChangeListener != null) {
						userChangeListener.userUpdated(dbUser);
					}
				}
			}
		} catch (BaseServerBusinessException e) {
			e.printStackTrace();
		}
	}

	private void getUser(SecurityUser securityUser, BaseUser example) throws BaseServerBusinessException {
		example.setPassword(HashUtil.sha1Hash(securityUser.password()));
		example.setSuperUser(securityUser.superUser());
		Set<Role> roles = new HashSet<Role>();
		for (int i = 0; i < securityUser.roles().length; i++) {
			String roleName = securityUser.roles()[i];
			List<Role> res = roleApi.findByName(roleName);
			if (res != null && !res.isEmpty())
				roles.add(res.get(0));
		}
		example.setRoles(roles);

	}

	private void saveOrUpdateRole(SecurityRole securityRole) {
		try {
			Role example = new Role();
			example.setName(securityRole.name());
			List<Role> resultList = roleApi.findByName(securityRole.name());
			if (resultList == null || resultList.isEmpty()) {
				getRole(securityRole, example);
				roleApi.saveNotSecure(example);
			} else {
				Role dbRole = resultList.get(0);
				Set<Operation> operations = dbRole.getOperations();
				Set<String> dbRoleOpTreeNames = new HashSet<String>();
				for (Operation operationEntity : operations) {
					dbRoleOpTreeNames.add(operationEntity.getEntityName());
				}
				boolean operationsAreEqual = true;
				if (securityRole.operations().length == dbRoleOpTreeNames.size()) {
					for (String dbRoleOpTN : dbRoleOpTreeNames) {
						if (Arrays.binarySearch(securityRole.operations(), dbRoleOpTN) == -1) {
							operationsAreEqual = false;
						}
					}
				} else {
					operationsAreEqual = false;
				}
				if (!securityRole.description().equals(dbRole.getDescription()) || !operationsAreEqual) {
					getRole(securityRole, dbRole);
					roleApi.saveNotSecure(dbRole);
				}
			}
		} catch (BaseServerBusinessException e) {
			e.printStackTrace();
		}
	}

	private void getRole(SecurityRole securityRole, Role role) throws BaseServerBusinessException {
		role.setDescription(securityRole.description());
		Set<Operation> secRoleOperations = new HashSet<>();
		for (int i = 0; i < securityRole.operations().length; i++) {
			List<Operation> dbOps = operationApi.findByName(securityRole.operations()[i]);
			if (dbOps != null && dbOps.size() > 0)
				secRoleOperations.add(dbOps.get(0));
		}
		role.setOperations(secRoleOperations);
	}

	private void saveOrUpdateOperation(SecurityOperation securityOperation) {
		Operation example = new Operation();
		example.setName(securityOperation.operationName());
		List<Operation> resultList = operationApi.findByName(securityOperation.operationName());
		if (resultList == null || resultList.isEmpty()) {
			example.setDescription(securityOperation.displayName());
			example.setEntityName(securityOperation.operationEntityName());
			example.setGroupName(securityOperation.oprationGroup());
			operationApi.saveNotSecure(example);
		} else {
			Operation dbOperation = resultList.get(0);
			if (!dbOperation.getDescription().equals(securityOperation.displayName())) {
				dbOperation.setDescription(securityOperation.displayName());
				example.setEntityName(securityOperation.operationEntityName());
				example.setGroupName(securityOperation.oprationGroup());
				operationApi.saveNotSecure(dbOperation);
			}
		}
	}

	@Autowired
	public void setOperationApi(OperationService operationApi) {
		this.operationApi = operationApi;
	}

	@Autowired
	public void setRoleApi(RoleService roleApi) {
		this.roleApi = roleApi;
	}

	@Autowired
	public void setUserApi(BaseUserService userApi) {
		this.userApi = userApi;
	}

	@Autowired
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	@Autowired(required = false)
	public void setUserChangeListener(IUserChangeListener userChangeListener) {
		this.userChangeListener = userChangeListener;
	}
}
