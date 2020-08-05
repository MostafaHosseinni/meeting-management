package ir.mine.project.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ir.mine.project.base.rest.BaseRestFulServiceSecure;
import ir.mine.project.domain.MeetingRoom;
import ir.mine.project.service.MeetingRoomService;
import ir.mine.project.service.dto.MeetingRoomDTO;

/**
 * REST controller for managing MeetingRoom.
 */
@RestController
@RequestMapping("/MeetingRoom")
public class MeetingRoomResource
		extends BaseRestFulServiceSecure<MeetingRoom, MeetingRoomDTO, Long, MeetingRoomService> {

	private static final String ENTITY_NAME = "meetingroom";

	public MeetingRoomResource(MeetingRoomService meetingroomService) {
		super(meetingroomService);
		setENTITY_NAME(ENTITY_NAME);
	}
}
