	package ir.mine.project.web.rest;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import ir.mine.project.base.rest.BaseRestFulServiceSecure;
import ir.mine.project.domain.Holiday;
import ir.mine.project.service.HolidayService;
import ir.mine.project.service.dto.HolidayDTO;
import ir.mine.project.service.dto.HolidayReceivedDTO;
import ir.mine.project.service.dto.projectionsdto.TestBriefDTO;

/**
 * REST controller for managing Holiday.
 */
@RestController
@RequestMapping("/Holiday")
public class HolidayResource extends BaseRestFulServiceSecure<Holiday, HolidayDTO, Long, HolidayService , TestBriefDTO> {

	private static final String ENTITY_NAME = "holiday";

	public HolidayResource(HolidayService holidayService) {
		super(holidayService);
		setENTITY_NAME(ENTITY_NAME);
	}

	@GetMapping("/getAllHoliday/{dayDiff}")
	@Timed
	public ResponseEntity<List<HolidayReceivedDTO>> getAllHoliday(@PathVariable ("dayDiff") Integer dayDiff) {
		List<Holiday> page = baseService.findAllHolidayInWeek(dayDiff);
		return ResponseEntity.ok(convertListEntityToDTOMethod(page, HolidayReceivedDTO.class));
	}

	@GetMapping("/getFirstHoliday")
	@Timed
	public ResponseEntity<HolidayDTO> getFirstHoliday(@RequestParam ZonedDateTime holiDate) {
		Holiday page = baseService.findHolidayInDay(holiDate);
		return ResponseEntity.ok(convertEntityToDTO(page));
	}

}
