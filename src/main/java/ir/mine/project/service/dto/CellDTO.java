package ir.mine.project.service.dto;

import ir.mine.project.base.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for the Meeting entity.
 */
@Getter
@Setter
public class CellDTO extends BaseDTO<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;

	private Boolean isHoliday;
	
	private Boolean isMeeting;

}
