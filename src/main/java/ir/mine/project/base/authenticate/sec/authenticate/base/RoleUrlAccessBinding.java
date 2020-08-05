package ir.mine.project.base.authenticate.sec.authenticate.base;

public class RoleUrlAccessBinding {

	private String[] role;
	private String[] url;
	
	public RoleUrlAccessBinding(String[] url, String[] role) {
		this.url = url;
		this.role = role;
	}

	public String[] getRole() {
		return role;
	}

	public void setRole(String[] role) {
		this.role = role;
	}

	public String[] getUrl() {
		return url;
	}

	public void setUrl(String[] url) {
		this.url = url;
	}

}
