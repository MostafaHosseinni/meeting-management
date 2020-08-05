package ir.mine.project.service;

import java.util.List;

import ir.mine.project.base.service.BaseService;
import ir.mine.project.domain.Profile;
import ir.mine.project.service.dto.PasswordDTO;

/**
 * Service Interface for managing profile.
 */
public interface ProfileService extends BaseService<Profile, Long> {

	Profile getCurrentProfile();

	void updateLastLoginTime(String username);

	Long activatedProfileOnThisMounth();

	List<Profile> findAllInvitees();

	Profile changePassword(PasswordDTO dto);

	Profile findFirstByUserName(String username);

	void addAdminUser(Long id);
}
