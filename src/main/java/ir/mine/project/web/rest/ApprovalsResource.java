package ir.mine.project.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ir.mine.project.base.rest.BaseRestFulServiceSecure;
import ir.mine.project.domain.Approvals;
import ir.mine.project.service.ApprovalsService;
import ir.mine.project.service.dto.ApprovalsDTO;

/**
 * REST controller for managing Approvals.
 */
@RestController
@RequestMapping("/Approvals")
public class ApprovalsResource
		extends BaseRestFulServiceSecure<Approvals, ApprovalsDTO, Long, ApprovalsService> {

	private static final String ENTITY_NAME = "approvals";

	public ApprovalsResource(ApprovalsService approvalsService) {
		super(approvalsService);
		setENTITY_NAME(ENTITY_NAME);
	}
}
