package ir.mine.project.domain.enumeration;

import ir.mine.project.base.dto.IHasDisplayName;

/**
 * The Template enumeration.
 */
public enum MeetingStatus implements IHasDisplayName {
	NEW, CONFIRMED, REJECT;

	@Override
	public String displayName() {
		return toString();
	}

	@Override
	public String toString() {
		switch (this) {
		case NEW:
			return "جدید";
		case CONFIRMED:
			return "تایید شده";
		case REJECT:
			return "رد شده";
		default:
			break;
		}
		return super.toString();
	}

}
