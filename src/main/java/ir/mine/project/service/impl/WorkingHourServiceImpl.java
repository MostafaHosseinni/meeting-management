package ir.mine.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.mine.project.base.authorization.autoupdate.annot.SecurityModel;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityOperation;
import ir.mine.project.base.service.BaseServiceImpl;
import ir.mine.project.domain.WorkingHour;
import ir.mine.project.domain.enumeration.WeekDays;
import ir.mine.project.repository.WorkingHourRepository;
import ir.mine.project.service.MeetingService;
import ir.mine.project.service.WorkingHourService;

/**
 * Service Implementation for managing BatteryLog.
 */
@Service("WorkingHourServiceImpl")
@Transactional
@SecurityModel(operations = {
		@SecurityOperation(operationName = "workinghour:create", operationEntityName = "workinghour", displayName = "", oprationGroup = "workinghour"),
		@SecurityOperation(operationName = "workinghour:read", operationEntityName = "workinghour", displayName = "", oprationGroup = "workinghour"),
		@SecurityOperation(operationName = "workinghour:update", operationEntityName = "workinghour", displayName = "", oprationGroup = "workinghour"),
		@SecurityOperation(operationName = "workinghour:delete", operationEntityName = "workinghour", displayName = "", oprationGroup = "workinghour"), })

public class WorkingHourServiceImpl extends BaseServiceImpl<WorkingHour, Long, WorkingHourRepository>
		implements WorkingHourService {

	@Autowired
	MeetingService meetingService;

	public WorkingHourServiceImpl(WorkingHourRepository workinghourRepository) {
		super(workinghourRepository);
	}

	@Override
	public Integer getMaxWorkingHour(Integer dayDiff) {
		Integer maxMeeting = meetingService.getMaxMeeting(dayDiff);
		Integer maxEndTime = baseRepository.getMaxEndTime();
		if (maxEndTime != null && maxMeeting == null) {
			return maxEndTime;
		} else if (maxEndTime == null && maxMeeting != null) {
			return maxMeeting;
		} else if (maxEndTime > maxMeeting) {
			return maxEndTime;
		} else {
			return maxMeeting;
		}
	}

	@Override
	public Integer getMinWorkingHour(Integer dayDiff) {
		Integer minMeeting = meetingService.getMinMeeting(dayDiff);
		Integer minStartTime = baseRepository.getMinStartTime();
		if (minStartTime == null && minMeeting == null)
			return null;
		else if (minStartTime != null && minMeeting == null) {
			return minStartTime;
		} else if (minStartTime == null && minMeeting != null) {
			return minMeeting;
		} else if (minStartTime < minMeeting) {
			return minStartTime;
		} else {
			return minMeeting;
		}
	}

	@Override
	public WorkingHour findFirstWorkingHour(WeekDays workDay) {
		return baseRepository.findFirstByWorkDay(workDay);
	}

}
