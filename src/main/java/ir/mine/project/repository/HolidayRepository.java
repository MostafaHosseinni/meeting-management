package ir.mine.project.repository;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import ir.mine.project.base.repository.BaseRepository;
import ir.mine.project.domain.Holiday;

/**
 * Spring Data repository for the Holiday entity.
 */
@Repository
public interface HolidayRepository extends BaseRepository<Holiday, Long> {

	List<Holiday> findAllByHolidayDateBetween(ZonedDateTime startDate, ZonedDateTime endDate);
	
	Holiday findFirstByHolidayDate(ZonedDateTime holidayDate);
	

}
