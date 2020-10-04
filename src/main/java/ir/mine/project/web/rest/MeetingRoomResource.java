package ir.mine.project.web.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import ir.mine.project.base.rest.BaseRestFulServiceSecure;
import ir.mine.project.domain.MeetingRoom;
import ir.mine.project.service.MeetingRoomService;
import ir.mine.project.service.dto.MeetingRoomDTO;
import ir.mine.project.service.dto.projectionsdto.TestBriefDTO;

/**
 * REST controller for managing MeetingRoom.
 */
@RestController
@RequestMapping("/MeetingRoom")
public class MeetingRoomResource
		extends BaseRestFulServiceSecure<MeetingRoom, MeetingRoomDTO, Long, MeetingRoomService,TestBriefDTO> {

	private static final String ENTITY_NAME = "meetingroom";

	public MeetingRoomResource(MeetingRoomService meetingroomService) {
		super(meetingroomService);
		setENTITY_NAME(ENTITY_NAME);
	}

	@GetMapping("/findAllValid")
	@Timed
	public ResponseEntity<List<MeetingRoomDTO>> findAllValid() {
		return ResponseEntity.ok(convertListEntityToDTO(baseService.findAllValid()));
	}

	@Override
	public ResponseEntity<List<MeetingRoomDTO>> getAllNotPageable() {
		return findAllValid();
	}
}
