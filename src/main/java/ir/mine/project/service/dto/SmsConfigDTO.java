package ir.mine.project.service.dto;

import ir.mine.project.base.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * A DTO for the SmsConfig entity.
 */
@Getter
@Setter
public class SmsConfigDTO extends BaseDTO<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ipAddress;

	private String ipPort;

	private String merchantId;

}
