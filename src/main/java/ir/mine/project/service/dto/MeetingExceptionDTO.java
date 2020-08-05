package ir.mine.project.service.dto;

import ir.mine.project.base.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the Holiday entity.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingExceptionDTO extends BaseDTO<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String titleEroor;

}
