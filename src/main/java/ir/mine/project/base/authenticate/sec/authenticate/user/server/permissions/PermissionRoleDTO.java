package ir.mine.project.base.authenticate.sec.authenticate.user.server.permissions;

import java.util.List;

public class PermissionRoleDTO {

	List<String> roleList;
	List<String> operationList;

	public PermissionRoleDTO(List<String> roleList, List<String> operationList) {
		super();
		this.roleList = roleList;
		this.operationList = operationList;
	}

	public List<String> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}

	public List<String> getOperationList() {
		return operationList;
	}

	public void setOperationList(List<String> operationList) {
		this.operationList = operationList;
	}

}
