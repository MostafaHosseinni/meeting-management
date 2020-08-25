package ir.mine.project.service;

import java.util.List;

import ir.mine.project.base.service.BaseService;
import ir.mine.project.domain.ServiceType;

/**
 * Service Interface for managing ServiceType.
 */
public interface ServiceTypeService extends BaseService<ServiceType, Long> {
	
	List<ServiceType> findAllValid();

}
