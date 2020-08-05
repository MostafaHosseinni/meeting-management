package ir.mine.project.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ir.mine.project.base.rest.BaseRestFulServiceSecure;
import ir.mine.project.domain.SmsConfig;
import ir.mine.project.service.SmsConfigService;
import ir.mine.project.service.dto.SmsConfigDTO;

/**
 * REST controller for managing SmsConfig.
 */
@RestController
@RequestMapping("/SmsConfig")
public class SmsConfigResource
		extends BaseRestFulServiceSecure<SmsConfig, SmsConfigDTO, Long, SmsConfigService> {

	private static final String ENTITY_NAME = "smsconfig";

	public SmsConfigResource(SmsConfigService smsconfigService) {
		super(smsconfigService);
		setENTITY_NAME(ENTITY_NAME);
	}
}
