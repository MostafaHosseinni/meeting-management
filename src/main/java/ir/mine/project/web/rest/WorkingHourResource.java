package ir.mine.project.web.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import ir.mine.project.base.Util.date.CalenderUtil;
import ir.mine.project.base.rest.BaseRestFulServiceSecure;
import ir.mine.project.domain.WorkingHour;
import ir.mine.project.domain.enumeration.WeekDays;
import ir.mine.project.service.WorkingHourService;
import ir.mine.project.service.dto.WorkingHourDTO;

/**
 * REST controller for managing WorkingHour.
 */
@RestController
@RequestMapping("/WorkingHour")
public class WorkingHourResource
		extends BaseRestFulServiceSecure<WorkingHour, WorkingHourDTO, Long, WorkingHourService> {

	private static final String ENTITY_NAME = "workinghour";

	public WorkingHourResource(WorkingHourService workinghourService) {
		super(workinghourService);
		setENTITY_NAME(ENTITY_NAME);
	}

	@GetMapping("/getMinWorkingHour/{dayDiff}")
	@Timed
	public ResponseEntity<Integer> getMinWorkingHour(@PathVariable("dayDiff") Integer dayDiff) {
		return new ResponseEntity<>(baseService.getMinWorkingHour(dayDiff), HttpStatus.OK);
	}

	@GetMapping("/getMaxWorkingHour/{dayDiff}")
	@Timed
	public ResponseEntity<Integer> getMaxWorkingHour(@PathVariable("dayDiff") Integer dayDiff) {
		return new ResponseEntity<>(baseService.getMaxWorkingHour(dayDiff), HttpStatus.OK);
	}

	@GetMapping("/getFirstWorkingHour/")
	@Timed
	public ResponseEntity<WorkingHourDTO> getFirstWorkingHour(@PathVariable WeekDays workDay) {
		WorkingHour page = baseService.findFirstWorkingHour(workDay);
		return new ResponseEntity<>(convertEntityToDTO(page), HttpStatus.OK);
	}

	@GetMapping("/findAllWithDate/{dayDiff}")
	@Timed
	public ResponseEntity<List<WorkingHourDTO>> findAllWithDate(@PathVariable("dayDiff") Integer dayDiff) {

		Date date = CalenderUtil.getDifferentDate(new Date(), dayDiff * 7);

		Date firstDayOfWeek = CalenderUtil.getFirstDayWeek(date);

		List<WorkingHour> findAll = baseService.findAll();

		List<WorkingHourDTO> dtos = new ArrayList<WorkingHourDTO>();

		for (int i = 0; i < findAll.size(); i++) {
			WorkingHourDTO dto = convertEntityToDTO(findAll.get(i));
			dto.setDayDate(CalenderUtil.dateToJalaliCalendar(CalenderUtil.getDifferentDate(firstDayOfWeek, i)));
			dtos.add(dto);
		}

		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

}
