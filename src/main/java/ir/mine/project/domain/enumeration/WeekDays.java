package ir.mine.project.domain.enumeration;

import ir.mine.project.base.dto.IHasDisplayName;

/**
 * The Template enumeration.
 */
public enum WeekDays implements IHasDisplayName {
	SATURDAY, SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY;

	@Override
	public String displayName() {
		return toString();
	}

	@Override
	public String toString() {
		switch (this) {
		case SATURDAY:
			return "شنبه";
		case SUNDAY:
			return "یک شنبه";
		case MONDAY:
			return "دوشنبه";
		case TUESDAY:
			return "سه شنبه";
		case WEDNESDAY:
			return "چهارشنبه";
		case THURSDAY:
			return "پنج شنبه";
		case FRIDAY:
			return "جمعه";
		default:
			break;
		}
		return super.toString();
	}

	public static WeekDays getValueOf(Integer weekDay) {
		switch (weekDay) {
		case 0:
			return MONDAY;
		case 1:
			return TUESDAY;
		case 2:
			return WEDNESDAY;
		case 3:
			return THURSDAY;
		case 4:
			return FRIDAY;
		case 5:
			return SATURDAY;
		case 6:
			return SUNDAY;
		default:
			return null;
		}

	}

}
