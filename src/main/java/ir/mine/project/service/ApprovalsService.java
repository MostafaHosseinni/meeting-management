package ir.mine.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ir.mine.project.base.service.BaseService;
import ir.mine.project.domain.Approvals;

/**
 * Service Interface for managing Approvals.
 */
public interface ApprovalsService extends BaseService<Approvals, Long> {

	Page<Approvals> findAllApprovalsForCurrentUser(Pageable pageable);

}
