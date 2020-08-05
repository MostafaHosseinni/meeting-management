package ir.mine.project.service;

import java.time.ZonedDateTime;
import java.util.List;

import ir.mine.project.base.service.BaseService;
import ir.mine.project.domain.Holiday;

/**
 * Service Interface for managing Holiday.
 */
public interface HolidayService extends BaseService<Holiday, Long> {

	List<Holiday> findAllHolidayInWeek(Integer dayDiff);
	
	Holiday findHolidayInDay(ZonedDateTime holiDate);
}
