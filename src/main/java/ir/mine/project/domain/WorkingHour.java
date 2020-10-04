package ir.mine.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;

import ir.mine.project.base.domin.BaseEntity;
import ir.mine.project.domain.enumeration.WeekDays;
import lombok.Getter;
import lombok.Setter;

/**
 * A workinghour.
 */
@Entity
@Table(name = "workinghour")
@Getter
@Setter

public class WorkingHour extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private WeekDays workDay;

	@Range(min = 0, max = 22)
	private Integer startTime;

	@Range(min = 1, max = 23)
	private Integer endTime;

	private String startTimeStr;

	private String endTimeStr;

}
