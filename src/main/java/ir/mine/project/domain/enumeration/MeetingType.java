package ir.mine.project.domain.enumeration;

import ir.mine.project.base.dto.IHasDisplayName;

/**
 * The Template enumeration.
 */
public enum MeetingType implements IHasDisplayName {
	MEETING, APPOINMENT;

	@Override
	public String displayName() {
		return toString();
	}

	@Override
	public String toString() {
		switch (this) {
		case MEETING:
			return "جلسه";
		case APPOINMENT:
			return "قرار";
		default:
			break;
		}
		return super.toString();
	}

	public static MeetingType getValueOf(String string) {
		MeetingType[] values = values();
		for (MeetingType meetingType : values) {
			if (string.equals(meetingType.toString()))
				return meetingType;
		}
		return null;
	}

}
