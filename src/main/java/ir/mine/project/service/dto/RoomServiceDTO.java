package ir.mine.project.service.dto;

import ir.mine.project.base.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for the RoomService entity.
 */
@Getter
@Setter
public class RoomServiceDTO extends BaseDTO<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer serviceCount;

	private ServiceTypeDTO serviceType;

}
