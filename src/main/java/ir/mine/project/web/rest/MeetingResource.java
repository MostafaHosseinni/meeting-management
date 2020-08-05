package ir.mine.project.web.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import ir.mine.project.base.rest.BaseRestFulServiceSecure;
import ir.mine.project.domain.Meeting;
import ir.mine.project.domain.enumeration.MeetingStatus;
import ir.mine.project.service.MeetingService;
import ir.mine.project.service.dto.MeetingDTO;
import ir.mine.project.service.dto.MeetingExceptionDTO;
import ir.mine.project.service.dto.MeetingReceivedDTO;

/**
 * REST controller for managing Meeting.
 */
@RestController
@RequestMapping("/Meeting")
public class MeetingResource extends BaseRestFulServiceSecure<Meeting, MeetingDTO, Long, MeetingService> {

	private static final String ENTITY_NAME = "meeting";

	public MeetingResource(MeetingService meetingService) {
		super(meetingService);
		setENTITY_NAME(ENTITY_NAME);
	}

	@GetMapping("/getAllMeeting/{dayDiff}")
	@Timed
	public ResponseEntity<List<MeetingReceivedDTO>> getAllMeeting(@PathVariable("dayDiff") Integer dayDiff) {
		List<Meeting> page = baseService.findAllMeeting(dayDiff);
		return ResponseEntity.ok(convertListEntityToDTOMethod(page, MeetingReceivedDTO.class));
	}

	@GetMapping("/getAllMeetingOrderRoomId")
	@Timed
	public ResponseEntity<List<MeetingDTO>> getAllMeetingOrderRoomId(@PathVariable Long roomId,
			@PathVariable("dayDiff") Integer dayDiff) {
		List<Meeting> page = baseService.findAllMeetingOrderRoomId(roomId, dayDiff);
		return ResponseEntity.ok(convertListEntityToDTO(page));
	}

	@GetMapping("/getMaxMeeting/{dayDiff}")
	@Timed
	public ResponseEntity<Integer> getMaxMeeting(@PathVariable("dayDiff") Integer dayDiff) {
		return new ResponseEntity<>(baseService.getMaxMeeting(dayDiff), HttpStatus.OK);
	}

	@GetMapping("/getMinMeeting/{dayDiff}")
	@Timed
	public ResponseEntity<Integer> getMinMeeting(@PathVariable("dayDiff") Integer dayDiff) {
		return new ResponseEntity<>(baseService.getMinMeeting(dayDiff), HttpStatus.OK);
	}

	@PostMapping("/preCheck")
	@ResponseBody
	@Timed
	public ResponseEntity<MeetingExceptionDTO> preCheck(@RequestBody MeetingDTO meeting) {
		return new ResponseEntity<>(new MeetingExceptionDTO(baseService.preChecking(convertDTOToEntity(meeting))),
				HttpStatus.OK);
	}

	@GetMapping("/rejectMeeting/{id}")
	@Timed
	public ResponseEntity<MeetingDTO> rejectMeeting(@PathVariable Long id) {
		return ResponseEntity.ok(convertEntityToDTO(baseService.rejectMeeting(id, MeetingStatus.REJECT)));
	}

}
