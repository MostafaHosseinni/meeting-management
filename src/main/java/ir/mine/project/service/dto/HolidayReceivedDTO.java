package ir.mine.project.service.dto;

import ir.mine.project.base.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for the Holiday entity.
 */
@Getter
@Setter
public class HolidayReceivedDTO extends BaseDTO<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer holidayDate;

	private String description;

	private Boolean isHoliday;

}
