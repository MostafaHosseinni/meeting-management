package ir.mine.project.service.dto;

import ir.mine.project.base.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for the Holiday entity.
 */
@Getter
@Setter
public class HolidayDTO extends BaseDTO<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String holidayDate;

	private String description;

	private Boolean isHoliday;

}
