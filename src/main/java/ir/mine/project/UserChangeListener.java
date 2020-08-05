package ir.mine.project;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ir.mine.project.base.authenticate.sec.authenticate.user.server.user.BaseUser;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.user.BaseUserService;
import ir.mine.project.base.authorization.autoupdate.listener.IUserChangeListener;
import ir.mine.project.domain.Profile;
import ir.mine.project.service.ProfileService;

@Component
public class UserChangeListener implements IUserChangeListener {

	@Autowired
	ProfileService profileService;

	@Autowired
	Mapper mapper;

	@Autowired
	BaseUserService userService;
	
	@Override
	public void userSaved(BaseUser user) {
		if("admin".equals(user.getUserName())) {
			Profile profile = profileService.findFirstByUserName(user.getUserName());
			if(profile == null) {
				profileService.addAdminUser(user.getId());
			}
		}
	}

	@Override
	public void userUpdated(BaseUser user) {
//		userService.deleteNotSecure(user.getId());
//		profileService.saveNotSecure(mapper.map(user, Profile.class));
	}

}
