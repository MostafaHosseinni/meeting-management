
package ir.mine.project.service.dto;

import ir.mine.project.base.authenticate.sec.authenticate.user.shared.dto.BaseUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A DTO for the Organization entity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfileDTO extends BaseUserDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String profileType;
	
	private String post;
	
	private String lastLoginTime;
	
	private String previousLoginTime;

}
