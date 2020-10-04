package ir.mine.project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ir.mine.project.base.service.BaseService;
import ir.mine.project.domain.Meeting;
import ir.mine.project.domain.enumeration.MeetingStatus;
import ir.mine.project.service.dto.MeetingSearchDTO;

/**
 * Service Interface for managing Meeting.
 */
public interface MeetingService extends BaseService<Meeting, Long> {

	List<Meeting> findAllMeeting(Integer dayDiff);

	List<Meeting> findAllMeetingOrderRoomId(Long roomId, Integer dayDiff);

	Integer getMinMeeting(Integer dayDiff);

	Integer getMaxMeeting(Integer dayDiff);

	String preChecking(Meeting meeting);

	Meeting rejectMeeting(Long id, MeetingStatus status);

	Page<Meeting> searchMeeting(MeetingSearchDTO dto, Pageable pageable);

}
