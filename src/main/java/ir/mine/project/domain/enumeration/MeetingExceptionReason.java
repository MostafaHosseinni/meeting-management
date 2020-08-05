package ir.mine.project.domain.enumeration;

import ir.mine.project.base.dto.IHasDisplayName;

public enum MeetingExceptionReason implements IHasDisplayName {

	INVALID_WORKING_HOURS, DAY_IN_QUESTION_IS_HOLIDAY, DESIRED_ROOM_IS_FULL, PERSON_HAS_MEETING_AT_THIS_TIME;

	@Override
	public String displayName() {
		return toString();
	}

	@Override
	public String toString() {
		switch (this) {
		case INVALID_WORKING_HOURS:
			return "ساعت کاری نامعتبر";
		case DAY_IN_QUESTION_IS_HOLIDAY:
			return "روز مد نظر تعطیل است ";
		case DESIRED_ROOM_IS_FULL:
			return "اتاق مد نظر پر است";
		case PERSON_HAS_MEETING_AT_THIS_TIME:
			return "شخص مورد نظر جلسه دارد";
		default:
			return "";
		}
	}

}
