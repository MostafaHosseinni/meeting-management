package ir.mine.project.web.rest;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import io.swagger.annotations.ApiParam;
import ir.mine.project.base.rest.BaseRestFulServiceSecure;
import ir.mine.project.domain.Approvals;
import ir.mine.project.service.ApprovalsService;
import ir.mine.project.service.dto.ApprovalsDTO;
import ir.mine.project.service.dto.projectionsdto.TestBriefDTO;
import ir.mine.project.web.rest.util.PaginationUtil;

/**
 * REST controller for managing Approvals.
 */
@RestController
@RequestMapping("/Approvals")
public class ApprovalsResource extends BaseRestFulServiceSecure<Approvals, ApprovalsDTO, Long, ApprovalsService , TestBriefDTO> {

	private static final String ENTITY_NAME = "approvals";

	public ApprovalsResource(ApprovalsService approvalsService) {
		super(approvalsService);
		setENTITY_NAME(ENTITY_NAME);
	}

	@GetMapping("/getAllApprovalsForCurrentUser")
	@Timed
	public ResponseEntity<List<ApprovalsDTO>> getAllApprovalsForCurrentUser(@ApiParam Pageable pageable) {
		Page<Approvals> page = baseService.findAllApprovalsForCurrentUser(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "*/getAllApprovalsForCurrentUser");
		return new ResponseEntity<>(convertListEntityToDTO(page.getContent()), headers, HttpStatus.OK);
	}

}
