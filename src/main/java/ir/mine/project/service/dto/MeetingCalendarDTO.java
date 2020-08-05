package ir.mine.project.service.dto;

import java.util.ArrayList;
import java.util.List;

import ir.mine.project.base.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for the Meeting entity.
 */
@Getter
@Setter
public class MeetingCalendarDTO extends BaseDTO<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String day;

	private List<CellDTO> cells = new ArrayList<>();

}
