package ir.mine.project.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.mine.project.base.authorization.autoupdate.annot.SecurityModel;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityOperation;
import ir.mine.project.base.service.BaseServiceImpl;
import ir.mine.project.domain.RoomService;
import ir.mine.project.repository.RoomServiceRepository;
import ir.mine.project.service.RoomServiceService;

/**
 * Service Implementation for managing BatteryLog.
 */
@Service("RoomServiceServiceImpl")
@Transactional
@SecurityModel(operations = {
		@SecurityOperation(operationName = "roomservice:create", operationEntityName = "roomservice", displayName ="" , oprationGroup = "roomservice"),
		@SecurityOperation(operationName = "roomservice:read", operationEntityName = "roomservice", displayName = "", oprationGroup = "roomservice"),
		@SecurityOperation(operationName = "roomservice:update", operationEntityName = "roomservice", displayName = "", oprationGroup = "roomservice"),
		@SecurityOperation(operationName = "roomservice:delete", operationEntityName = "roomservice", displayName = "", oprationGroup = "roomservice"), })

public class RoomServiceServiceImpl extends BaseServiceImpl<RoomService, Long, RoomServiceRepository>
		implements RoomServiceService {


	public RoomServiceServiceImpl(RoomServiceRepository roomserviceRepository) {
		super(roomserviceRepository);
	}

}
