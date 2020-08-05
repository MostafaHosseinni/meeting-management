package ir.mine.project.base.authorization.config.auth.success;

import ir.mine.project.base.authenticate.sec.authenticate.config.authsuccess.ICredentialsIntoSession;


//@Component
public class CredentialsIntoSession implements ICredentialsIntoSession {

    @Override
    public void putCredentialsIntoSession() {
    }

//	@Autowired
//	private IUserApi userApi;
//
//	@Override
//	public void putCredentialsIntoSession() {
//		try {
//
//			String username = (String) session().getAttribute(
//					IBaseUserAPI.USERNAME_SESSION_PROPERTY_NAME);
//
//			UserEntity userEntity = userApi.getUserByUsername(username);
//			
//			if (userEntity == null){
//				throw new RuntimeException("USER " + username + " IS NOT FOUND IN DB");
//			}
//			
//			session().setAttribute(IUserApi.USER_SESSION_PROPERTY_NAME,
//					userEntity);
//
//			Set<RoleEntity> roles = userEntity.getRoles();
//			if (roles != null) {
//				session().setAttribute(IUserApi.ROLES_SESSION_PROPERTY_NAME,
//						new HashSet<RoleEntity>(roles));
//				Set<OperationEntity> allOperations = new HashSet<OperationEntity>();
//				for (RoleEntity roleEntity : roles) {
//					Set<OperationEntity> operations = roleEntity
//							.getOperations();
//					if (operations != null && !operations.isEmpty()) {
//						allOperations.addAll(operations);
//					}
//				}
//				session().setAttribute(
//						IUserApi.OPERATIONS_SESSION_PROPERTY_NAME,
//						allOperations);
//			}
//		} catch (BaseServerBusinessException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static HttpSession session() {
//		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
//				.currentRequestAttributes();
//		return attr.getRequest().getSession(true); // true == allow create
//	}

}
