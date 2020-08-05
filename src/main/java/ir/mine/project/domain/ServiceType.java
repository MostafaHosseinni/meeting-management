package ir.mine.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ir.mine.project.base.domin.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * A servicetype.
 */
@Entity
@Table(name = "servicetype")
@Getter
@Setter

public class ServiceType extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

}
