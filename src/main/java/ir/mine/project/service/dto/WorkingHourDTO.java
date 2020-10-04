package ir.mine.project.service.dto;

import ir.mine.project.base.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for the WorkingHour entity.
 */
@Getter
@Setter
public class WorkingHourDTO extends BaseDTO<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String workDay;

	private Integer startTime;

	private Integer endTime;
	
	private String dayDate;
	
	private String startTimeStr;

	private String endTimeStr;

}
