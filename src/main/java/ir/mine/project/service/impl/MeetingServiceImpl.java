package ir.mine.project.service.impl;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.mine.project.base.Util.date.CalenderUtil;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityModel;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityOperation;
import ir.mine.project.base.service.BaseServiceImpl;
import ir.mine.project.domain.Holiday;
import ir.mine.project.domain.Meeting;
import ir.mine.project.domain.Profile;
import ir.mine.project.domain.WorkingHour;
import ir.mine.project.domain.enumeration.MeetingExceptionReason;
import ir.mine.project.domain.enumeration.MeetingStatus;
import ir.mine.project.domain.enumeration.ProfileType;
import ir.mine.project.domain.enumeration.WeekDays;
import ir.mine.project.repository.MeetingRepository;
import ir.mine.project.service.HolidayService;
import ir.mine.project.service.MeetingRoomService;
import ir.mine.project.service.MeetingService;
import ir.mine.project.service.ProfileService;
import ir.mine.project.service.WorkingHourService;

/**
 * Service Implementation for managing BatteryLog.
 */
@Service("MeetingServiceImpl")
@Transactional
@SecurityModel(operations = {
		@SecurityOperation(operationName = "meeting:create", operationEntityName = "meeting", displayName = "", oprationGroup = "meeting"),
		@SecurityOperation(operationName = "meeting:read", operationEntityName = "meeting", displayName = "", oprationGroup = "meeting"),
		@SecurityOperation(operationName = "meeting:update", operationEntityName = "meeting", displayName = "", oprationGroup = "meeting"),
		@SecurityOperation(operationName = "meeting:delete", operationEntityName = "meeting", displayName = "", oprationGroup = "meeting"), })

public class MeetingServiceImpl extends BaseServiceImpl<Meeting, Long, MeetingRepository> implements MeetingService {

	@Autowired
	ProfileService profileService;

	@Autowired
	WorkingHourService workingHourService;

	@Autowired
	HolidayService holidayService;

	@Autowired
	MeetingRoomService meetingRoomService;

	public MeetingServiceImpl(MeetingRepository meetingRepository) {
		super(meetingRepository);
	}

	public Meeting save(Meeting t) {
		if (t.getId() == null) {
			t.setCreateDate(ZonedDateTime.now());
			t.setCreator(profileService.getCurrentProfile());
			t.setMeetingStatus(MeetingStatus.NEW);
		}
		ZonedDateTime meetingDate = t.getMeetingDate();
		meetingDate = meetingDate.plusHours(t.getStartTime());
		t.setMeetingDate(meetingDate);
		return super.save(t);
	}

	public String preChecking(Meeting t) {
		if (!checkHolidayOk(t)) {
			return MeetingExceptionReason.DAY_IN_QUESTION_IS_HOLIDAY.toString();
		} else if (!checkWorkingHourOk(t)) {
			return MeetingExceptionReason.INVALID_WORKING_HOURS.toString();
		} else if (!checkRoomEmpty(t)) {
			return MeetingExceptionReason.DESIRED_ROOM_IS_FULL.toString();
		} else if (!checkPersonFree(t)) {
			return MeetingExceptionReason.PERSON_HAS_MEETING_AT_THIS_TIME.toString();
		}
		return null;
	}

	private Boolean checkHolidayOk(Meeting t) {

		Holiday holiday = holidayService.findHolidayInDay(t.getMeetingDate());
		if (holiday != null) {
			if (holiday.getHolidayDate().equals(t.getMeetingDate())) {
				return false;

			}
		}
		return true;
	}

	private boolean checkWorkingHourOk(Meeting t) {
		WeekDays weekDay = WeekDays.getValueOf(t.getMeetingDate().getDayOfWeek().ordinal());
		WorkingHour workingHour = workingHourService.findFirstWorkingHour(weekDay);
		if (workingHour.getEndTime() == null && workingHour.getStartTime() == null) {
			return false;
		} else if (workingHour.getStartTime() > t.getEndTime() || workingHour.getEndTime() < t.getStartTime()) {
			return false;
		}
		return true;
	}

	private Boolean checkRoomEmpty(Meeting t) {

		ZonedDateTime startDate = CalenderUtil.getDayStart(t.getMeetingDate());
		ZonedDateTime endDate = CalenderUtil.getDayEnd(t.getMeetingDate());

		List<Meeting> meetings = baseRepository.findAllByMeetingRoom_idAndMeetingDateBetweenAndMeetingStatusNot(
				t.getMeetingRoom().getId(), startDate, endDate, MeetingStatus.REJECT);

		if (meetings != null && !meetings.isEmpty()) {

			for (Meeting meeting : meetings) {
				if ((meeting.getStartTime() <= t.getStartTime() && meeting.getEndTime() >= t.getStartTime())
						|| (meeting.getStartTime() >= t.getEndTime() && meeting.getEndTime() <= t.getEndTime())) {
					return false;
				}

			}

		}

		return true;
	}

	private Boolean checkPersonFree(Meeting t) {

		List<Long> inviteesId = new ArrayList<Long>();
		for (Profile profile : t.getInvitees()) {

			inviteesId.add(profile.getId());

		}
		ZonedDateTime stratDate = CalenderUtil.getDayStart(t.getMeetingDate());
		ZonedDateTime endDate = CalenderUtil.getDayEnd(t.getMeetingDate());
		List<Meeting> inviteesMeetings = baseRepository
				.findAllByInvitees_idInAndMeetingDateBetweenAndStartTimeBetweenAndEndTimeBetween(inviteesId, stratDate,
						endDate, t.getStartTime(), t.getEndTime(), t.getStartTime(), t.getEndTime());
		if (!inviteesMeetings.isEmpty())
			return false;

		return true;

	}

	@Override
	public List<Meeting> findAllMeeting(Integer dayDiff) {

		Profile currentProfile = profileService.getCurrentProfile();

		Date date = CalenderUtil.getDifferentDate(new Date(), dayDiff * 7);

		ZonedDateTime startDate = CalenderUtil.getFirstDayWeek(date).toInstant().atZone(ZoneId.systemDefault());

		ZonedDateTime endDate = CalenderUtil.getLastDayWeek(date).toInstant().atZone(ZoneId.systemDefault());

		if (currentProfile.getProfileType().equals(ProfileType.INVITEES)) {
			return baseRepository.findAllByInvitees_idAndMeetingDateBetweenAndMeetingStatusNot(currentProfile.getId(),
					startDate, endDate, MeetingStatus.REJECT);

		}
		return baseRepository.findAllByMeetingDateBetweenAndMeetingStatusNot(startDate, endDate, MeetingStatus.REJECT);
	}

	@Override
	public List<Meeting> findAllMeetingOrderRoomId(Long roomId, Integer dayDiff) {

		Date date = CalenderUtil.getDifferentDate(new Date(), dayDiff * 7);

		ZonedDateTime startDate = CalenderUtil.getFirstDayWeek(date).toInstant().atZone(ZoneId.systemDefault());

		ZonedDateTime endDate = CalenderUtil.getLastDayWeek(date).toInstant().atZone(ZoneId.systemDefault());

		return baseRepository.findAllByMeetingRoom_idAndMeetingDateBetweenAndMeetingStatusNot(roomId, startDate,
				endDate, MeetingStatus.REJECT);
	}

	@Override
	public Integer getMinMeeting(Integer dayDiff) {

		Profile currentProfile = profileService.getCurrentProfile();

		if (currentProfile.getProfileType().equals(ProfileType.INVITEES)) {
			List<Long> userId = new ArrayList<Long>();
			userId.add(currentProfile.getId());
			return baseRepository.getMinStartTimeForCurrentUserAndMeetingStatusNot(userId, MeetingStatus.REJECT);
		}
		return baseRepository.getMinStartTimeAndMeetingStatusNot(MeetingStatus.REJECT);
	}

	@Override
	public Integer getMaxMeeting(Integer dayDiff) {

		Profile currentProfile = profileService.getCurrentProfile();
		if (currentProfile.getProfileType().equals(ProfileType.INVITEES)) {
			List<Long> userId = new ArrayList<Long>();
			userId.add(currentProfile.getId());
			return baseRepository.getMaxEndTimeForCurrentUserAndMeetingStatusNot(userId, MeetingStatus.REJECT);
		}
		return baseRepository.getMaxEndTimeAndMeetingStatusNot(MeetingStatus.REJECT);
	}

	@Override
	public Meeting rejectMeeting(Long id, MeetingStatus status) {
		Meeting meeting = findOne(id);
		meeting.setCreator(profileService.getCurrentProfile());
		meeting.setMeetingStatus(status);
		meeting.setLastModifiedDate(ZonedDateTime.now());
		return super.save(meeting);
	}

}
