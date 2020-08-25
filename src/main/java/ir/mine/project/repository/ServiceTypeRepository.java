package ir.mine.project.repository;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import ir.mine.project.base.repository.BaseRepository;
import ir.mine.project.domain.ServiceType;

/**
 * Spring Data repository for the ServiceType entity.
 */
@Repository
public interface ServiceTypeRepository extends BaseRepository<ServiceType, Long> {

	List<ServiceType> findAllByExpireDateGreaterThan(ZonedDateTime expireDate);

}
