package ir.mine.project.base.authenticate.conditional.config.annotation.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ir.mine.project.base.Util.SpringUtils;
import ir.mine.project.base.authenticate.sec.authenticate.base.BaseUserDetail;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.permissions.IUserPermissionsProvider;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.permissions.PermissionRoleDTO;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.user.BaseUser;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.user.BaseUserService;
import ir.mine.project.base.excphndl.exceptionServer.BaseServerBusinessException;

public class BasicUserDetails implements BaseUserDetail {

    private static final long serialVersionUID = 1L;
    private String username;
    private Date lastLoginDate;
    private List<String> operationList;

    private BaseUser baseUserEntity;

    public BasicUserDetails(String username) {
        this.username = username;
        this.lastLoginDate = new Date();
        fetchUserFromDB();
    }

    protected void fetchUserFromDB() {
        BaseUserService userAPI = (BaseUserService) SpringUtils
                .getBean(BaseUserService.class);
        try {
            baseUserEntity = userAPI.findFirstByUsername(username);
            if (baseUserEntity == null)
                throw new BaseServerBusinessException();
        } catch (BaseServerBusinessException e) {
            throw new UsernameNotFoundException("User " + username + " Not Found!!!");
        }
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
        result.add(new SimpleGrantedAuthority("ROLE_AUTHENTICATED"));
        try {
            IUserPermissionsProvider permissionsProvider = (IUserPermissionsProvider) SpringUtils
                    .getBean(IUserPermissionsProvider.class);
            PermissionRoleDTO userAuthorities = permissionsProvider
                    .getUserAuthorities(username);
            List<String> roleList = userAuthorities.getRoleList();
            this.operationList = userAuthorities.getOperationList();
            if (roleList != null) {
                for (String role : roleList) {
                    result.add(new SimpleGrantedAuthority(role));
                }
            }
        } catch (BaseServerBusinessException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getPassword() {
        return baseUserEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return checkExpireDate(baseUserEntity.getExpireDate());
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BasicUserDetails other = (BasicUserDetails) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public List<String> getUserOperations() {
        return operationList;
    }

    private boolean checkExpireDate(Date expireDate) {

        return true;
//
//		if (expireDate != null ) {
//
//			Date nowDate = new Date();
//			if (nowDate.before(expireDate))
//				return true;
//			else
//				return false;
//		}
//		// else
//		// throw new UsernameNotFoundException("Expire Date Not Valid");
//		return false;
    }

}
