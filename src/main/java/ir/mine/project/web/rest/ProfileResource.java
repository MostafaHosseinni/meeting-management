package ir.mine.project.web.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import ir.mine.project.base.authenticate.sec.authenticate.user.server.operation.Operation;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.role.Role;
import ir.mine.project.base.rest.BaseRestFulServiceSecure;
import ir.mine.project.domain.Profile;
import ir.mine.project.service.ProfileService;
import ir.mine.project.service.dto.PasswordDTO;
import ir.mine.project.service.dto.ProfileDTO;

/**
 * REST controller for managing Profile.
 */
@RestController
@RequestMapping("/Profile")
public class ProfileResource extends BaseRestFulServiceSecure<Profile, ProfileDTO, Long, ProfileService> {

	private static final String ENTITY_NAME = "profile";

	public ProfileResource(ProfileService profileService) {
		super(profileService);
		setENTITY_NAME(ENTITY_NAME);
	}

	@GetMapping("/getCurrentUser")
	public ResponseEntity<ProfileDTO> getCurrentUserProfile() {
		return ResponseEntity.ok(convertEntityToDTO(baseService.getCurrentProfile()));
	}

	@GetMapping("/getUserOperations")
	@Timed
	public ResponseEntity<List<String>> getUserOperations() {
		Profile currentProfile = baseService.getCurrentProfile();
		List<String> names = new ArrayList<>();
		Set<Role> roles = currentProfile.getRoles();
		if (roles != null) {
			for (Role role : roles) {
				Set<Operation> operations = role.getOperations();
				for (Operation operation : operations) {
					names.add(operation.getName());
				}
			}
		}
		return ResponseEntity.ok(names);
	}

	@PostMapping("/changePassword")
	public ResponseEntity<ProfileDTO> changePassword(@RequestBody PasswordDTO dto) {
		return ResponseEntity.ok(convertEntityToDTO(baseService.changePassword(dto)));
	}

	@GetMapping("/getAllInvitees")
	public ResponseEntity<List<ProfileDTO>> getAllInvitees() {
		return ResponseEntity.ok(convertListEntityToDTO(baseService.findAllInvitees()));
	}

}
