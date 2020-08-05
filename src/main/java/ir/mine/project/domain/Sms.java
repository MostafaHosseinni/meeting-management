package ir.mine.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ir.mine.project.base.domin.BaseEntity;
import ir.mine.project.domain.enumeration.SmsStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * A sms.
 */
@Entity
@Table(name = "sms")
@Getter
@Setter

public class Sms extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	private String receiverNumber;

	private SmsStatus smsStatus;

}
