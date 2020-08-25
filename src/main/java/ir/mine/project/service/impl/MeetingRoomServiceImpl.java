package ir.mine.project.service.impl;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.mine.project.base.authorization.autoupdate.annot.SecurityModel;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityOperation;
import ir.mine.project.base.service.BaseServiceImpl;
import ir.mine.project.domain.MeetingRoom;
import ir.mine.project.repository.MeetingRoomRepository;
import ir.mine.project.service.MeetingRoomService;

/**
 * Service Implementation for managing BatteryLog.
 */
@Service("MeetingRoomServiceImpl")
@Transactional
@SecurityModel(operations = {
		@SecurityOperation(operationName = "meetingroom:create", operationEntityName = "meetingroom", displayName ="" , oprationGroup = "meetingroom"),
		@SecurityOperation(operationName = "meetingroom:read", operationEntityName = "meetingroom", displayName = "", oprationGroup = "meetingroom"),
		@SecurityOperation(operationName = "meetingroom:update", operationEntityName = "meetingroom", displayName = "", oprationGroup = "meetingroom"),
		@SecurityOperation(operationName = "meetingroom:delete", operationEntityName = "meetingroom", displayName = "", oprationGroup = "meetingroom"), })

public class MeetingRoomServiceImpl extends BaseServiceImpl<MeetingRoom, Long, MeetingRoomRepository>
		implements MeetingRoomService {


	public MeetingRoomServiceImpl(MeetingRoomRepository meetingroomRepository) {
		super(meetingroomRepository);
	}

	@Override
	public List<MeetingRoom> findAllValid() {
		return baseRepository.findAllByExpireDateGreaterThan(ZonedDateTime.now());
	}

}
