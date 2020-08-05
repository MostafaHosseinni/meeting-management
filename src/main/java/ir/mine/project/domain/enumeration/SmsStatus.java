package ir.mine.project.domain.enumeration;

import ir.mine.project.base.dto.IHasDisplayName;

/**
 * The Template enumeration.
 */
public enum SmsStatus implements IHasDisplayName {
	NEW,SENT,FAILED;

	@Override
	public String displayName() {
		return null;
	}
	

}
