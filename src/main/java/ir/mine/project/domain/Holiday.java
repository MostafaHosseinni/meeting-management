package ir.mine.project.domain;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ir.mine.project.base.domin.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * A holiday.
 */
@Entity
@Table(name = "holiday")
@Getter
@Setter

public class Holiday extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private ZonedDateTime holidayDate;

	private String description;


}
