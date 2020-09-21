
package ir.mine.project.repository;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ir.mine.project.base.repository.BaseRepository;
import ir.mine.project.domain.Meeting;
import ir.mine.project.domain.enumeration.MeetingStatus;

/**
 * Spring Data repository for the Meeting entity.
 */
@Repository
public interface MeetingRepository extends BaseRepository<Meeting, Long> {

	List<Meeting> findAllByMeetingRoom_idAndMeetingDateBetweenAndMeetingStatusNot(Long roomId, ZonedDateTime startDate,
			ZonedDateTime endDate, MeetingStatus status);

	List<Meeting> findAllByInvitees_idAndMeetingDateBetweenAndMeetingStatusNot(Long id, ZonedDateTime startDate,
			ZonedDateTime endDate, MeetingStatus status);

	List<Meeting> findAllByMeetingDateBetweenAndMeetingStatusNot(ZonedDateTime startDate, ZonedDateTime endDate,
			MeetingStatus status);

	List<Meeting> findAllByInvitees_idInAndMeetingDateBetweenAndStartTimeBetweenAndEndTimeBetween(List<Long> inviteesId,
			ZonedDateTime meetingDateFrom, ZonedDateTime meetingDateTo, Integer startTime1, Integer endTime1,
			Integer startTime2, Integer endTime2);

	@Query("select min(m.startTime) from Meeting m")
	Integer getMinStartTimeAndMeetingStatusNot(MeetingStatus status);

	@Query("select max(m.endTime) from Meeting m")
	Integer getMaxEndTimeAndMeetingStatusNot(MeetingStatus status);

	@Query("select min(m.startTime) from Meeting m join m.invitees inv where inv.id in (?1)")
	Integer getMinStartTimeForCurrentUserAndMeetingStatusNot(List<Long> id, MeetingStatus status);

	@Query("select max(m.endTime) from Meeting m join m.invitees inv where inv.id in (?1)")
	Integer getMaxEndTimeForCurrentUserAndMeetingStatusNot(List<Long> id, MeetingStatus status);



}
