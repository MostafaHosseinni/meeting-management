package ir.mine.project.repository;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import ir.mine.project.base.repository.BaseRepository;
import ir.mine.project.domain.MeetingRoom;


/**
 * Spring Data  repository for the MeetingRoom entity.
 */
@Repository
public interface MeetingRoomRepository extends BaseRepository<MeetingRoom, Long> {
	
	List<MeetingRoom> findAllByExpireDateGreaterThan(ZonedDateTime expireDate);

}
