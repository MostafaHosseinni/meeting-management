package ir.mine.project.service.dto;

import ir.mine.project.base.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for the ServiceType entity.
 */
@Getter
@Setter
public class ServiceTypeDTO extends BaseDTO<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;

	private String expireDate;

}
