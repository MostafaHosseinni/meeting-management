package ir.mine.project.service;

import ir.mine.project.base.service.BaseService;
import ir.mine.project.domain.WorkingHour;
import ir.mine.project.domain.enumeration.WeekDays;

/**
 * Service Interface for managing WorkingHour.
 */
public interface WorkingHourService extends BaseService<WorkingHour, Long> {

	Integer getMaxWorkingHour(Integer dayDiff);

	Integer getMinWorkingHour(Integer dayDiff);

	WorkingHour findFirstWorkingHour(WeekDays workDay);
	
	

}
