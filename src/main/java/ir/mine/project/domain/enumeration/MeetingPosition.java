package ir.mine.project.domain.enumeration;

import ir.mine.project.base.dto.IHasDisplayName;

/**
 * The Template enumeration.
 */
public enum MeetingPosition implements IHasDisplayName {
	HOST,GUEST;

	@Override
	public String displayName() {
		return toString();
	}
	@Override
	public String toString() {
		switch (this) {
		case HOST:
			return "میزبان";
		case GUEST:
			return "میهمان";
		default:
			break;
		}
		return super.toString();
	}

}
