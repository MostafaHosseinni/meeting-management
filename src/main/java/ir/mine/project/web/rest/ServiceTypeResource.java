package ir.mine.project.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ir.mine.project.base.rest.BaseRestFulServiceSecure;
import ir.mine.project.domain.ServiceType;
import ir.mine.project.service.ServiceTypeService;
import ir.mine.project.service.dto.ServiceTypeDTO;

/**
 * REST controller for managing ServiceType.
 */
@RestController
@RequestMapping("/ServiceType")
public class ServiceTypeResource
		extends BaseRestFulServiceSecure<ServiceType, ServiceTypeDTO, Long, ServiceTypeService> {

	private static final String ENTITY_NAME = "servicetype";

	public ServiceTypeResource(ServiceTypeService servicetypeService) {
		super(servicetypeService);
		setENTITY_NAME(ENTITY_NAME);
	}
}
