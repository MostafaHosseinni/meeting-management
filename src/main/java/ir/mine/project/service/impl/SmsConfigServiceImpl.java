package ir.mine.project.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.mine.project.base.authorization.autoupdate.annot.SecurityModel;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityOperation;
import ir.mine.project.base.service.BaseServiceImpl;
import ir.mine.project.domain.SmsConfig;
import ir.mine.project.repository.SmsConfigRepository;
import ir.mine.project.service.SmsConfigService;

/**
 * Service Implementation for managing BatteryLog.
 */
@Service("SmsConfigServiceImpl")
@Transactional
@SecurityModel(operations = {
		@SecurityOperation(operationName = "smsconfig:create", operationEntityName = "smsconfig", displayName ="" , oprationGroup = "smsconfig"),
		@SecurityOperation(operationName = "smsconfig:read", operationEntityName = "smsconfig", displayName = "", oprationGroup = "smsconfig"),
		@SecurityOperation(operationName = "smsconfig:update", operationEntityName = "smsconfig", displayName = "", oprationGroup = "smsconfig"),
		@SecurityOperation(operationName = "smsconfig:delete", operationEntityName = "smsconfig", displayName = "", oprationGroup = "smsconfig"), })

public class SmsConfigServiceImpl extends BaseServiceImpl<SmsConfig, Long, SmsConfigRepository>
		implements SmsConfigService {


	public SmsConfigServiceImpl(SmsConfigRepository smsconfigRepository) {
		super(smsconfigRepository);
	}

}
