package ir.mine.project.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ir.mine.project.base.repository.BaseRepository;
import ir.mine.project.domain.WorkingHour;
import ir.mine.project.domain.enumeration.WeekDays;

/**
 * Spring Data repository for the WorkingHour entity.
 */
@Repository
public interface WorkingHourRepository extends BaseRepository<WorkingHour, Long> {

	@Query("select min(w.startTime) from WorkingHour w")
	Integer getMinStartTime();

	@Query("select max(w.endTime) from WorkingHour w")
	Integer getMaxEndTime();
	
	WorkingHour findFirstByWorkDay(WeekDays workDay);

	
}
