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
	
	public static MeetingPosition getValueOf(String string) {
		MeetingPosition[] values = values();
		for (MeetingPosition meetingPosition : values) {
			if (string.equals(meetingPosition.toString()))
				return meetingPosition;
		}
		return null;
	}


}
