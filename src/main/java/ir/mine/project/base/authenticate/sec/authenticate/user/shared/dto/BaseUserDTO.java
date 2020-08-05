package ir.mine.project.base.authenticate.sec.authenticate.user.shared.dto;

import java.util.Date;
import java.util.List;

import ir.mine.project.base.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BaseUserDTO extends BaseDTO<Long> {

	private String firstName;

	private String lastName;

	private String userName;

	private String email;

	private String phoneNumber;

	private String mobileNumber;

	private String password;

	private Boolean isActive;

	private Date expireDate;

	private Boolean superUser;

	private List<RoleDTO> roles;
}
