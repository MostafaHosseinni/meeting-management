package ir.mine.project.service.dto;

import java.util.ArrayList;
import java.util.List;

import ir.mine.project.base.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for the Approvals entity.
 */
@Getter
@Setter
public class ApprovalsDTO extends BaseDTO<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String approval;

	private List<String> outOfApproval;

	private String createDate;

	private String startTime;

	private String endTime;

	private MeetingDTO meeting;

	private List<AgendaDTO> approvalRul;

	private ProfileDTO creator;

	private List<ProfileDTO> presents = new ArrayList<>();

	private List<ProfileDTO> absents = new ArrayList<>();

}
