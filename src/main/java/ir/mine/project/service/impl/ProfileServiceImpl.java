package ir.mine.project.service.impl;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.mine.project.base.Util.hash.HashUtil;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.authentication.IAuthenticationAPI;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.operationinfo.OperationDescription;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.operationinfo.OperationGroupName;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.role.Role;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.role.RoleService;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityModel;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityOperation;
import ir.mine.project.base.service.BaseServiceImpl;
import ir.mine.project.domain.Profile;
import ir.mine.project.domain.enumeration.ProfileType;
import ir.mine.project.repository.ProfileRepository;
import ir.mine.project.service.ProfileService;
import ir.mine.project.service.dto.PasswordDTO;
import ir.mine.project.staticvariable.RoleNames;
import lombok.SneakyThrows;

/**
 * Service Implementation for managing Profile.
 */
@Service("ProfileServiceImpl")
@Transactional
@SecurityModel(operations = {
		@SecurityOperation(operationName = "profile:create", operationEntityName = "Profile", displayName = OperationDescription.profileC, oprationGroup = OperationGroupName.profile),
		@SecurityOperation(operationName = "profile:read", operationEntityName = "Profile", displayName = OperationDescription.profileR, oprationGroup = OperationGroupName.profile),
		@SecurityOperation(operationName = "profile:update", operationEntityName = "Profile", displayName = OperationDescription.profileU, oprationGroup = OperationGroupName.profile),
		@SecurityOperation(operationName = "profile:delete", operationEntityName = "Profile", displayName = OperationDescription.profileD, oprationGroup = OperationGroupName.profile), })
public class ProfileServiceImpl extends BaseServiceImpl<Profile, Long, ProfileRepository> implements ProfileService {

	@Autowired
	private IAuthenticationAPI authenticationAPI;

	@Autowired
	private RoleService roleService;

	public ProfileServiceImpl(ProfileRepository profileRepository) {
		super(profileRepository);
	}

	@Override
	@SneakyThrows
	public Profile getCurrentProfile() {
		return baseRepository.findFirstByUserName(authenticationAPI.getCurrentUsername());
	}

	@Override
	public Profile save(Profile t) {

		t.setPassword(HashUtil.sha1Hash(t.getPassword()));

		t.setRoles(new HashSet<>());

		if (t.getProfileType() != null) {
			if (ProfileType.SECRATERY == t.getProfileType()) {
				Role role = roleService.findOneByName(RoleNames.SECRATERY);
				role.getOperations();
				t.getRoles().add(role);
			} else if (ProfileType.ADMIN == t.getProfileType()) {
				Role role = roleService.findOneByName(RoleNames.ADMIN);
				role.getOperations();
				t.getRoles().add(role);
			} else if (ProfileType.INVITEES == t.getProfileType()) {
				Role role = roleService.findOneByName(RoleNames.INVITEES);
				role.getOperations();
				t.getRoles().add(role);
			}
		}
		return super.save(t);
	}

	@Override
	public void updateLastLoginTime(String username) {
		Profile profile = baseRepository.findFirstByUserName(username);
		profile.setPreviousLoginTime(profile.getLastLoginTime());
		profile.setLastLoginTime(ZonedDateTime.now());
		super.save(profile);
	}

	@Override
	public Long activatedProfileOnThisMounth() {
		ZonedDateTime to = ZonedDateTime.now();
		ZonedDateTime from = to.minusMonths(1);
		return baseRepository.countByLastLoginTimeBetween(from, to);
	}

	@Override
	public Profile changePassword(PasswordDTO dto) {
		Profile profile = getCurrentProfile();
		if (profile.getPassword().equals(HashUtil.sha1Hash(dto.getCurrentPassword()))
				&& dto.getNewPassword().equals(dto.getConfirmNewPassword())) {
			profile.setPassword(HashUtil.sha1Hash(dto.getNewPassword()));
			return super.save(profile);
		}
		return null;
	}

	@Override
	public Profile findFirstByUserName(String username) {
		return baseRepository.findFirstByUserName(username);
	}

	@Override
	public void addAdminUser(Long id) {
		baseRepository.addAdminUser(id);
	}

	@Override
	public List<Profile> findAllInvitees() {
		return baseRepository.findByProfileType(ProfileType.INVITEES);
	}

}
