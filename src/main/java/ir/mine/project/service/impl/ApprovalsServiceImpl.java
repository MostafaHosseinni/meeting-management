package ir.mine.project.service.impl;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.mine.project.base.authorization.autoupdate.annot.SecurityModel;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityOperation;
import ir.mine.project.base.service.BaseServiceImpl;
import ir.mine.project.domain.Approvals;
import ir.mine.project.repository.ApprovalsRepository;
import ir.mine.project.service.ApprovalsService;
import ir.mine.project.service.ProfileService;

/**
 * Service Implementation for managing BatteryLog.
 */
@Service("ApprovalsServiceImpl")
@Transactional
@SecurityModel(operations = {
		@SecurityOperation(operationName = "approvals:create", operationEntityName = "approvals", displayName = "", oprationGroup = "approvals"),
		@SecurityOperation(operationName = "approvals:read", operationEntityName = "approvals", displayName = "", oprationGroup = "approvals"),
		@SecurityOperation(operationName = "approvals:update", operationEntityName = "approvals", displayName = "", oprationGroup = "approvals"),
		@SecurityOperation(operationName = "approvals:delete", operationEntityName = "approvals", displayName = "", oprationGroup = "approvals"), })

public class ApprovalsServiceImpl extends BaseServiceImpl<Approvals, Long, ApprovalsRepository>
		implements ApprovalsService {

	@Autowired
	ProfileService profileService;

	public ApprovalsServiceImpl(ApprovalsRepository approvalsRepository) {
		super(approvalsRepository);
	}

	public Approvals save(Approvals t) {
		if (t.getId() == null) {
			t.setCreateDate(ZonedDateTime.now());
			t.setCreator(profileService.getCurrentProfile());
		}
		return super.save(t);
	}

}
