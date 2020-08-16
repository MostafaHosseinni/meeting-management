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
			return "جلسه در ساعت کاری نامعتبر تنظیم شده است آیا از ثبت آن اطمینان دارید ؟";
		case DAY_IN_QUESTION_IS_HOLIDAY:
			return "روز مد نظر شما برای ثبت جلسه تعطیل است آیا از ثبت آن اطمینان دارید ؟  ";
		case DESIRED_ROOM_IS_FULL:
			return "در ساعت انتخابی شما اتاق مد نظر اشغال است آیا از برگزاری همزمان جلسه اطمینان دارید ؟";
		case PERSON_HAS_MEETING_AT_THIS_TIME:
			return "بعضی از اعضای جلسه در زمان مورد نظر جلسه دیگری دارند آیا از ثبت جلسه با حضور این افراد اطمینان دارید ؟ ";
		default:
			return "";
		}
	}

}
