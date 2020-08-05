package ir.mine.project.repository;

import org.springframework.stereotype.Repository;

import ir.mine.project.base.repository.BaseRepository;
import ir.mine.project.domain.ServiceType;


/**
 * Spring Data  repository for the ServiceType entity.
 */
@Repository
public interface ServiceTypeRepository extends BaseRepository<ServiceType, Long> {

}
