package ir.mine.project.service;

import java.util.List;

import ir.mine.project.base.service.BaseService;
import ir.mine.project.domain.MeetingRoom;

/**
 * Service Interface for managing MeetingRoom.
 */
public interface MeetingRoomService extends BaseService<MeetingRoom, Long> {

	List<MeetingRoom> findAllValid();

}
