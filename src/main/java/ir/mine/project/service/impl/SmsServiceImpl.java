package ir.mine.project.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.mine.project.base.authorization.autoupdate.annot.SecurityModel;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityOperation;
import ir.mine.project.base.service.BaseServiceImpl;
import ir.mine.project.domain.Sms;
import ir.mine.project.repository.SmsRepository;
import ir.mine.project.service.SmsService;

/**
 * Service Implementation for managing BatteryLog.
 */
@Service("SmsServiceImpl")
@Transactional
@SecurityModel(operations = {
		@SecurityOperation(operationName = "sms:create", operationEntityName = "sms", displayName ="" , oprationGroup = "sms"),
		@SecurityOperation(operationName = "sms:read", operationEntityName = "sms", displayName = "", oprationGroup = "sms"),
		@SecurityOperation(operationName = "sms:update", operationEntityName = "sms", displayName = "", oprationGroup = "sms"),
		@SecurityOperation(operationName = "sms:delete", operationEntityName = "sms", displayName = "", oprationGroup = "sms"), })

public class SmsServiceImpl extends BaseServiceImpl<Sms, Long, SmsRepository>
		implements SmsService {


	public SmsServiceImpl(SmsRepository smsRepository) {
		super(smsRepository);
	}

}
