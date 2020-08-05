package ir.mine.project.service.dto;

import java.util.ArrayList;
import java.util.List;

import ir.mine.project.base.dto.BaseDTO;
import ir.mine.project.domain.Profile;
import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for the Meeting entity.
 */
@Getter
@Setter
public class MeetingReceivedDTO extends BaseDTO<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;

	private String createDate;

	private String lastModifiedDate;

	private Integer meetingDate;

	private Integer startTime;

	private Integer endTime;

	private String meetingStatus;

	private String meetingType;

	private String meetingPosition;

	private String meetingService;

	private String agenda;

	private MeetingRoomDTO meetingRoom;

	private ProfileDTO creator;

	private ProfileDTO Secretary;

	private ProfileDTO boss;

	private List<ProfileDTO> invitees = new ArrayList<>();

}
