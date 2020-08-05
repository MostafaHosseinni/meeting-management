package ir.mine.project.service.impl;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.mine.project.base.Util.date.CalenderUtil;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityModel;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityOperation;
import ir.mine.project.base.service.BaseServiceImpl;
import ir.mine.project.domain.Holiday;
import ir.mine.project.repository.HolidayRepository;
import ir.mine.project.service.HolidayService;

/**
 * Service Implementation for managing BatteryLog.
 */
@Service("HolidayServiceImpl")
@Transactional
@SecurityModel(operations = {
		@SecurityOperation(operationName = "holiday:create", operationEntityName = "holiday", displayName = "", oprationGroup = "holiday"),
		@SecurityOperation(operationName = "holiday:read", operationEntityName = "holiday", displayName = "", oprationGroup = "holiday"),
		@SecurityOperation(operationName = "holiday:update", operationEntityName = "holiday", displayName = "", oprationGroup = "holiday"),
		@SecurityOperation(operationName = "holiday:delete", operationEntityName = "holiday", displayName = "", oprationGroup = "holiday"), })

public class HolidayServiceImpl extends BaseServiceImpl<Holiday, Long, HolidayRepository> implements HolidayService {

	public HolidayServiceImpl(HolidayRepository holidayRepository) {
		super(holidayRepository);
	}

	@Override
	public List<Holiday> findAllHolidayInWeek(Integer dayDiff) {
		
		Date date = CalenderUtil.getDifferentDate(new Date(), dayDiff * 7);

		ZonedDateTime startDate = CalenderUtil.getFirstDayWeek(date).toInstant().atZone(ZoneId.systemDefault());

		ZonedDateTime endDate = CalenderUtil.getLastDayWeek(date).toInstant().atZone(ZoneId.systemDefault());

		return baseRepository.findAllByHolidayDateBetween(startDate, endDate);
	}

	@Override
	public Holiday findHolidayInDay(ZonedDateTime holiDate) {
		return baseRepository.findFirstByHolidayDate(holiDate);
	}

}
