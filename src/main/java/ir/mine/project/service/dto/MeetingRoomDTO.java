package ir.mine.project.service.dto;

import java.util.ArrayList;
import java.util.List;

import ir.mine.project.base.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for the MeetingRoom entity.
 */
@Getter
@Setter
public class MeetingRoomDTO extends BaseDTO<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String roomName;

	private String capacity;

	private String address;

	private List<RoomServiceDTO> service = new ArrayList<>();

}
