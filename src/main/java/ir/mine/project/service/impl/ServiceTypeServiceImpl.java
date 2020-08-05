package ir.mine.project.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.mine.project.base.authorization.autoupdate.annot.SecurityModel;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityOperation;
import ir.mine.project.base.service.BaseServiceImpl;
import ir.mine.project.domain.ServiceType;
import ir.mine.project.repository.ServiceTypeRepository;
import ir.mine.project.service.ServiceTypeService;

/**
 * Service Implementation for managing BatteryLog.
 */
@Service("ServiceTypeServiceImpl")
@Transactional
@SecurityModel(operations = {
		@SecurityOperation(operationName = "servicetype:create", operationEntityName = "servicetype", displayName ="" , oprationGroup = "servicetype"),
		@SecurityOperation(operationName = "servicetype:read", operationEntityName = "servicetype", displayName = "", oprationGroup = "servicetype"),
		@SecurityOperation(operationName = "servicetype:update", operationEntityName = "servicetype", displayName = "", oprationGroup = "servicetype"),
		@SecurityOperation(operationName = "servicetype:delete", operationEntityName = "servicetype", displayName = "", oprationGroup = "servicetype"), })

public class ServiceTypeServiceImpl extends BaseServiceImpl<ServiceType, Long, ServiceTypeRepository>
		implements ServiceTypeService {


	public ServiceTypeServiceImpl(ServiceTypeRepository servicetypeRepository) {
		super(servicetypeRepository);
	}

}
