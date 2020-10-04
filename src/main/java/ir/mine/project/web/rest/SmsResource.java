package ir.mine.project.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ir.mine.project.base.rest.BaseRestFulServiceSecure;
import ir.mine.project.domain.Sms;
import ir.mine.project.service.SmsService;
import ir.mine.project.service.dto.SmsDTO;
import ir.mine.project.service.dto.projectionsdto.TestBriefDTO;

/**
 * REST controller for managing Sms.
 */
@RestController
@RequestMapping("/Sms")
public class SmsResource
		extends BaseRestFulServiceSecure<Sms, SmsDTO, Long, SmsService,TestBriefDTO> {

	private static final String ENTITY_NAME = "sms";

	public SmsResource(SmsService smsService) {
		super(smsService);
		setENTITY_NAME(ENTITY_NAME);
	}
}
